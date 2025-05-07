import {NgModule} from '@angular/core';
import {GraphsComponent} from './graphs/graphs.component';

@NgModule({
  declarations: [
    GraphsComponent
  ],
  exports: [
    GraphsComponent
  ]
})
export class ComponentModule {
}
