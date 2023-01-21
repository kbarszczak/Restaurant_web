import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DishesComponent} from "./dishes/dishes.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
    { path: "", component: HomeComponent},
    { path: "home", component: HomeComponent},
    { path: "dishes", component: HomeComponent},
    { path: "basket", component: HomeComponent},
    { path: "login", component: HomeComponent},
    { path: "register", component: HomeComponent},
    // { path: "dishes", component: DishesComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
