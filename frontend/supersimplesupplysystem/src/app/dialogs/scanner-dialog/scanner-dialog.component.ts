import {AfterViewChecked, AfterViewInit, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import Quagga from '@ericblade/quagga2';
import {UpdateService} from '../../services/update.service';
import {MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition,} from '@angular/material/snack-bar';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-scanner-dialog',
  templateUrl: './scanner-dialog.component.html',
  styleUrls: ['./scanner-dialog.component.css']
})
export class ScannerDialogComponent implements OnInit, AfterViewInit {
  errorMessage: string;

  private lastScannedCode: string;
  private lastScannedCodeDate: number;
  public environment = environment;

  constructor(private changeDetectorRef: ChangeDetectorRef,
              private updateService: UpdateService,
              private snackBar: MatSnackBar,
              private dialogRef: MatDialogRef<ScannerDialogComponent>) {
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    if (!navigator.mediaDevices || !(typeof navigator.mediaDevices.getUserMedia === 'function')) {
      this.errorMessage = 'getUserMedia is not supported';
      return;
    }

    this.dialogRef.beforeClosed().subscribe(() => {
      Quagga.stop();
    });

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

  public onBarcodeScanned(code: string): void {
    Quagga.stop();
    this.dialogRef.close(code);
  }
}
