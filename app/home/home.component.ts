import { Component, OnInit } from "@angular/core";

@Component({
    selector: "Home",
    moduleId: module.id,
    templateUrl: "./home.component.html",
    styleUrls: ["./home.component.css"]
})
export class HomeComponent implements OnInit {
    onButtonTap(): void {
        console.log("Button was pressed");
    }

    htmlString: string = "<span><h1>HtmlView demo in <font color=\"blue\">NativeScript</font> App</h1></span>";

    textFieldValue: string[] = [];

    perguntas: string[] = ['idade', 'nome', 'rg'];



    constructor() {
    }

    ngOnInit(): void {
    }

    logger(palavra: string): void{
        console.log(palavra);
    }
}
