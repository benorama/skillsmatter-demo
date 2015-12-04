import {Component, EventEmitter, Input, Output} from 'angular2/angular2'

@Component({
  selector: 'gallery',
  templateUrl: '/app/components/gallery.html'
})
export class GalleryComponent {

  @Input() characters: String[]
  @Output() vote: EventEmitter<String> = new EventEmitter<String>();

  onVoteClick(character: String) {
    this.vote.next(character);
  }

}
