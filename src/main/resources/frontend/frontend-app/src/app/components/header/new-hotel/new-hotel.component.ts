import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UploadService} from "../../../service/upload.service";
import {catchError, map} from "rxjs/operators";
import {HttpErrorResponse, HttpEventType} from "@angular/common/http";
import {of} from "rxjs";
import {HttpClientService} from "../../../service/http-client.service";

export interface OwnerForCreation {
  name: string;
  dateOfBirth: Date;
  address: string;
}

@Component({
  selector: 'app-new-hotel',
  templateUrl: './new-hotel.component.html',
  styleUrls: ['./new-hotel.component.css']
})
export class NewHotelComponent implements OnInit {

  public hotelForm: FormGroup;
  public starsFilter: number = null;
  @ViewChild("fileUpload", {static: false}) fileUpload: ElementRef;files  = [];
  public uploadButtonClicked: boolean;
  private imageURL: string;

  constructor(private readonly router: Router, private readonly uploadService: UploadService,
              private readonly httpService: HttpClientService) {

  }

  ngOnInit() {
    this.hotelForm = new FormGroup({
      name: new FormControl('', [Validators.maxLength(60)]),
      descr: new FormControl('', [Validators.maxLength(100)]),
      category: new FormControl('', [ Validators.maxLength(20)]),
      activities: new FormControl('', [Validators.maxLength(100)]),
      stars: new FormControl('', [Validators.maxLength(10)]),
      price: new FormControl('', [Validators.maxLength(10)]),
      rating: new FormControl('', [Validators.maxLength(10)]),
      city: new FormControl('', [Validators.maxLength(50)]),
      activity: new FormControl('', [Validators.maxLength(50)]),
      otherFilters: new FormControl('', [Validators.maxLength(50)])
    });
  }

  uploadFile(file) {
    const formData = new FormData();
    formData.append('file', file.data);
    file.inProgress = true;
    this.uploadService.upload(formData).pipe(
      map(event => {
        switch (event.type) {
          case HttpEventType.UploadProgress:
            file.progress = Math.round(event.loaded * 100 / event.total);
            break;
          case HttpEventType.Response:
            return event;
        }
      }),
      catchError((error: HttpErrorResponse) => {
        file.inProgress = false;
        return of(`${file.data.name} upload failed.`);
      })).subscribe((event: any) => {
      if (typeof (event) === 'object') {
        this.uploadButtonClicked = true;
        console.log('jel to to', event.body.link);
        this.imageURL = event.body.link;
        console.log('value is ' , this.hotelForm.get('stars').value);
      }
    });
  }

  onClick() {
    const fileUpload = this.fileUpload.nativeElement;fileUpload.onchange = () => {
      for (let index = 0; index < fileUpload.files.length; index++)
      {
        const file = fileUpload.files[index];
        this.files.push({ data: file, inProgress: false, progress: 0});
      }
      this.uploadFiles();
    };
    fileUpload.click();
  }

  public onCancel = () => {
    this.router.navigateByUrl('');
  };

  private uploadFiles() {
    this.fileUpload.nativeElement.value = '';
    this.files.forEach(file => {
      this.uploadFile(file);
    });
  }

  public insertNewHotel() {
    console.log('im,age url', this.hotelForm.get('otherFilters').value);
    this.httpService.insertNewHotel(this.hotelForm.get('name').value, this.hotelForm.get('descr').value,
      this.hotelForm.get('category').value, this.hotelForm.get('price').value, this.hotelForm.get('rating').value,
      this.hotelForm.get('stars').value, this.hotelForm.get('city').value,
      this.hotelForm.get('activities').value, this.hotelForm.get('otherFilters').value, this.imageURL).subscribe(response => {
        console.log('response', response);
    });
  }
}
