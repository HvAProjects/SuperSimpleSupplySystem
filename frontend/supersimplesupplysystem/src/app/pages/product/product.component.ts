import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {ProductsLocation} from '../../models/ProductsLocation';
import {ActivatedRoute, Params} from '@angular/router';
import {LocationService} from '../../services/location.service';
import {MatDialog} from '@angular/material/dialog';
import {ProductService} from '../../services/product.service';
import {Product} from '../../models/Product';
import {AddLocationDialogComponent} from '../../dialogs/add-location-dialog/add-location-dialog.component';
import {AddProductDialogComponent} from '../../dialogs/add-product-dialog/add-product-dialog.component';
import {DeleteProductDialogComponent} from '../../dialogs/delete-product-dialog/delete-product-dialog.component';
import {FormControl, FormGroup} from '@angular/forms';
import {finalize, tap} from 'rxjs/operators';
import {MatSort} from '@angular/material/sort';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['barcode', 'name', 'quantity', 'amount', 'expirationDate', 'location', 'action'];
  householdId;
  dataSource: MatTableDataSource<Product>;
  searchForm: FormGroup;
  searchbar = '';
  locationFilter = '';
  locationsHousehold: ProductsLocation[];
  data = [];

  @ViewChild(MatSort) sort: MatSort;


  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private dialog: MatDialog,
    private locationService: LocationService
  ) {
    this.route.params.subscribe((params: Params) => this.householdId = +params.householdId);
    this.retrieveLocationsFromHousehold();
    this.searchFormInit();
    this.getProducts();
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
  }

  openDeleteProductDialog(product): void {
    const dialogRef = this.dialog.open(DeleteProductDialogComponent, {
      width  : '380px',
      disableClose: false,
      data: product
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== null) {
        console.log(product);
        this.productService.deleteProducts(product.id, result).subscribe(res => {
          if (res.success) {
            this.getProducts();
          }
        });
      }
    });
  }

  openAddProductDialog(): void {
    const dialogRef = this.dialog.open(AddProductDialogComponent, {
      width  : '380px',
      disableClose: false,
      data: {householdId: this.householdId}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.product !== null && result.location !== null) {
        this.productService.addProductToLocation(result.locationId, result.product).subscribe(res => {
          if (res.success) {
            this.getProducts();
          }
        });
      }
    });
  }

  private getProducts(): void {
    this.productService.getProductsByHousehold(this.householdId).subscribe((data: Product[]) => {
      const dataSource = [];
      // Onderstaande code omdat de response niet automatisch naar een Product type vertaald wordt door javascript
      // Daardoor wordt de date als string geïnterpreteerd. Jeeej javascript
      // Typescript maakt het nog vager, omdat je er daardoor vanuit gaat dat het wel een Date type is
      for (const item of data) {
        const product = new Product();
        product.id = item.id;
        product.expirationDate = new Date(item.expirationDate);
        product.barcode = item.barcode;
        product.amount = item.amount;
        product.name = item.name;
        product.quantity = item.quantity;
        product.location = item.location;
        dataSource.push(product);
      }
      this.dataSource = new MatTableDataSource<Product>(dataSource);
      this.dataSource.filterPredicate = this.getFilterPredicate();
      this.applyFilter();
      this.dataSource.sort = this.sort;
    });
  }


  searchFormInit() {
    this.searchForm = new FormGroup({
      searchbar: new FormControl('')
    });
  }

  applyFilter() {
    const search = this.searchForm.get('searchbar').value;
    this.searchbar = (search === null || search === '') ? '' : search;
    this.locationFilter = (this.locationFilter === null) ? '' : this.locationFilter;

    // create string of our searching values and split if by '$'
    const filterValue = this.searchbar + '$' + this.locationFilter;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  getFilterPredicate() {
    return (row: Product, filters: string) => {
      // split string per '$' to array
      const filterArray = filters.split('$');
      const search = filterArray[0];
      const location = filterArray[1];

      const matchFilter = [];

      // Fetch data from row
      const columnName = row.name;
      const columnBarcode = row.barcode;
      const columnLocation = row.location.name;


      // verify fetching data by our searching values
      const customFilterSearch = columnName.toLowerCase().includes(search) || columnBarcode.toLowerCase().includes(search);
      const customFilterLocation = columnLocation.toLowerCase().includes(location);

      // push boolean values into array
      matchFilter.push(customFilterSearch);
      matchFilter.push(customFilterLocation);
      // return true if all values in array is true
      // else return false
      return matchFilter.every(Boolean);
    };
  }



  public retrieveLocationsFromHousehold() {
    this.locationService.getLocationsOfHousehold(this.householdId).pipe(
      tap(locations => {
        this.locationsHousehold = locations;
      })
    ).subscribe();
  }



}
