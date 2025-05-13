import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private api = `${environment.api}/posts`;
  private http = inject(HttpClient);

  constructor() { }

  public buscarPosts() {
    return this.http.get(`${this.api}`);
  }
}
