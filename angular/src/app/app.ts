import {Component, NgIf} from 'angular2/angular2'
import {Headers, Http, Response, HTTP_BINDINGS} from "angular2/http"
import {EndComponent} from './components/end'
import {GalleryComponent} from './components/gallery'
import {StartComponent} from './components/start'

@Component({
    directives: [
      NgIf,
      EndComponent,
      GalleryComponent,
      StartComponent
    ],
    providers: [HTTP_BINDINGS],
    selector: 'app',
    templateUrl: '/app/app.html'
})
export class AppComponent {

  characters: String[] = [
    'bb-8',
    'boba-fett',
    'c-3po',
    'chewbacca',
    'darth_maul',
    'darth-vader',
    'princess_amidala',
    'r2-d2',
    'royal_guard',
    'stormtrooper',
    'the_emperor',
    'yoda'
  ]
  quizId: String = "1"
  selectedCharacter: String
  state: String = "start"
  userName: String

  constructor(public http: Http) {
  }

  onLogin(userName: String) {
    this.userName = userName
    this.state = 'gallery'
  }

  onVote(character: String): void {
    var apiUrl = "http://localhost:8080/api/quizzes/1/votes"
    //var apiUrl = "https://wwv05o5gz0.execute-api.us-east-1.amazonaws.com/beta/quizzes/1/votes"
    var body = JSON.stringify({
      answerId: character,
      userName: this.userName
    })
    var headers = new Headers()
    headers.append('Content-Type', 'application/json')
    this.http.post(apiUrl, body, {headers: headers})
        .map((res: Response) => res.json())
        .subscribe(
          // Success
          (response: Response) => {
              console.log("Vote response", response)
              this.selectedCharacter = character
              this.state = 'end'
            },
          // Error
          error => console.error("Vote error", error),
          // Complete
          () => console.log('Vote Complete')
        );
  }

}
