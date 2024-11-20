import { Component } from '@angular/core';
import { DownloadService } from '../../services/download.service';

@Component({
  selector: 'app-download',
  standalone: true,
  imports: [],
  templateUrl: './download.component.html',
  styleUrl: './download.component.scss'
})
export class DownloadComponent {

  constructor (private downloadService : DownloadService) {

  }

  ngOnInit() {
    this.downloadService.download().subscribe((data: any) => {
      this.downloadFile(data);
    }),
    (error : any ) => {
      console.log(error);
    }
  }
  
  downloadFile(data: BlobPart) {
    const blob = new Blob([data], { type: 'application/zip' });
    const url= window.URL.createObjectURL(blob);
    window.open(url);
  }

}