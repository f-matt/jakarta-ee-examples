import { Component } from '@angular/core';
import { IndexService } from '../../services/index.service';

@Component({
  selector: 'app-index',
  standalone: true,
  imports: [],
  templateUrl: './index.component.html',
  styleUrl: './index.component.scss'
})
export class IndexComponent {

  protected message: string = "";

  constructor(private indexService : IndexService) {

  }

  ngOnInit() {
    this.indexService.index().subscribe((r: string) => {
      this.message = r;
    }),
    (error : any ) => {
      console.log(error);
    }
  }


}
