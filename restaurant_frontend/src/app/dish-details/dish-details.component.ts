import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-dish-details',
    templateUrl: './dish-details.component.html',
    styleUrls: ['./dish-details.component.css']
})
export class DishDetailsComponent implements OnInit {

    // todo: tell if the is error

    private route: ActivatedRoute
    id: string | null

    constructor(route: ActivatedRoute) {
        this.route = route
        this.id = ""
    }

    ngOnInit(): void {
        this.id = this.route.snapshot.paramMap.get("id")
    }

}
