import {ChangeDetectorRef, Component, OnInit, AfterViewInit, ElementRef, ViewChild} from '@angular/core';
import Quagga from 'quagga';
import {UpdateService} from "../../services/update.service";
import {MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition,} from '@angular/material/snack-bar';


@Component({
  selector: 'app-scanner',
  templateUrl: './scanner.component.html',
  styleUrls: ['./scanner.component.css']
})
export class ScannerComponent implements OnInit, AfterViewInit {
  errorMessage: string;

  horizontalPosition: MatSnackBarHorizontalPosition = 'start';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  private lastScannedCode: string;
  private lastScannedCodeDate: number;

  constructor(private changeDetectorRef: ChangeDetectorRef,
              private updateService: UpdateService,
              private snackBar: MatSnackBar) {

  }

  ngOnInit(): void {
    }

  ngAfterViewInit(): void {
    if (!navigator.mediaDevices || !(typeof navigator.mediaDevices.getUserMedia === 'function')) {
      this.errorMessage = 'getUserMedia is not supported';
      return;
    }

    Quagga.init({
        inputStream: {
          constraints: {
            facingMode: 'environment'
          },
          area: { // defines rectangle of the detection/localization area
            top: '40%',    // top offset
            right: '0%',  // right offset
            left: '0%',   // left offset
            bottom: '40%'  // bottom offset
          },
        },
        decoder: {
          readers: ['ean_reader']
        },
      },
      (err) => {
        if (err) {
          this.errorMessage = `QuaggaJS could not be initialized, err: ${err}`;
        } else {
          Quagga.start();
          Quagga.onDetected((res) => {
            this.onBarcodeScanned(res.codeResult.code);
          });
        }
      });

    setTimeout(() => {
      this.updateService.checkForUpdates();
    }, 10000);
  }

  public onBarcodeScanned(code: string) {
    alert("Code die gescant is: " + code);
    // this.openSnackBar('De EAN code die gescant is: ' + code, 'OK');
  }

  public openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
      horizontalPosition: this.horizontalPosition,
      verticalPosition: this.verticalPosition,
    });
  }

}
