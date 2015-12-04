import {Component, Input} from 'angular2/angular2';

@Component({
  selector: 'end',
  templateUrl: '/app/components/end.html'
})
export class EndComponent {

  @Input() character: String

}
