import { Component, inject } from '@angular/core';
import { PostService } from '../../services/post.service';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-post',
  imports: [AsyncPipe],
  templateUrl: './post.component.html',
  styleUrl: './post.component.scss',
})
export class PostComponent {
  private postService = inject(PostService);
  // protected posts: any[] = [];
  protected posts$ = new Observable<any>();

  constructor() {
    this.buscarPosts();
  }

  public buscarPosts() {
      this.posts$ = this.postService.buscarPosts();
      
    // this.postService.buscarPosts().subscribe((posts: any) => {
    //   console.log(posts);
    //   this.posts = posts;
    // });
  }
}
