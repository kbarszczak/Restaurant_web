import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DishesComponent} from "./dishes/dishes.component";
import {HomeComponent} from "./home/home.component";
import {BasketComponent} from "./basket/basket.component";
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {DishDetailsComponent} from "./dish-details/dish-details.component";

const routes: Routes = [
    {path: "", component: HomeComponent},
    {path: "home", component: HomeComponent},
    {
        path: "dishes",
        component: DishesComponent,
        children: [
            {path: ":id", component: DishDetailsComponent}
        ]
    },
    {path: "basket", component: BasketComponent},
    {path: "login", component: LoginComponent},
    {path: "register", component: RegisterComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
