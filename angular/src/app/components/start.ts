import {Component, EventEmitter, Output} from 'angular2/angular2';

@Component({
  selector: 'start',
  templateUrl: '/app/components/start.html'
})
export class StartComponent {

  @Output() login: EventEmitter<String> = new EventEmitter<String>()

  onSubmit(userName: String) {
    this.login.next(userName)
  }

}
