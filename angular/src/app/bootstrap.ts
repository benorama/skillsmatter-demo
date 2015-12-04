//main entry point
import {bind, bootstrap} from 'angular2/angular2';
import {HTTP_BINDINGS} from 'angular2/http';
import {AppComponent} from './app';

bootstrap(AppComponent, [
  HTTP_BINDINGS
])
  .catch(err => console.error(err));
