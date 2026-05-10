import { Component, OnDestroy } from '@angular/core';
import { interval, Subject, Subscription, takeUntil } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-filho',
  imports: [AsyncPipe],
  templateUrl: './filho.html',
  styleUrl: './filho.css',
})
export class Filho implements OnDestroy {
  protected values = interval(1000);
  private id = Math.floor(Math.random() * 100);
  //Solução 1
  // private sub = new Subscription();
  //Solução 2
  // private unsub$ = new Subject(); //Observable que pode emitir valores

  constructor() {
    //Quando o componente filho é destruído, o observable não morre sozinho - memory leak (vazamento de memória).
    //Soluçao 1 - Criar uma referência para o observable - Subscription e quando o componente for destruído, desinscrever o observable.
    // this.sub = this.values.subscribe(() => {
    //   console.log(`Código: ${this.id} ainda imprimindo.`);
    // });

    /*
    Solução 2 - Utilizar pipe takeUntil(). O observable será destruído quando um outro observable emitir um valor (qualquer valor).
    Nesse caso, o valor é emitido quando o componente é destruído.
     */
    // this.values.pipe(takeUntil(this.unsub$)).subscribe(() => {
    //   console.log(`Código: ${this.id} ainda imprimindo.`);
    // });

    //Solução 3 - Utilizar pipe takeUntilDestroyed().
    this.values.pipe(takeUntilDestroyed()).subscribe(() => {
      console.log(`Código: ${this.id} ainda imprimindo.`);
    });
  }

  ngOnDestroy() {
    //Solução 1
    // this.sub.unsubscribe();
    //console.log("Solução 1")
    //Solução 2
    // this.unsub$.next(true);
    // console.log("Solução 2");
  }
}
