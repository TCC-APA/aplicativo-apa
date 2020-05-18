(global["webpackJsonp"] = global["webpackJsonp"] || []).push([[0],{

/***/ "./home/home-routing.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomeRoutingModule", function() { return HomeRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__("@angular/core");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_angular_core__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var nativescript_angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__("nativescript-angular/router");
/* harmony import */ var nativescript_angular_router__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(nativescript_angular_router__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _home_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__("./home/home.component.ts");



var routes = [
    { path: "", component: _home_component__WEBPACK_IMPORTED_MODULE_2__["HomeComponent"] }
];
var HomeRoutingModule = /** @class */ (function () {
    function HomeRoutingModule() {
    }
    HomeRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [nativescript_angular_router__WEBPACK_IMPORTED_MODULE_1__["NativeScriptRouterModule"].forChild(routes)],
            exports: [nativescript_angular_router__WEBPACK_IMPORTED_MODULE_1__["NativeScriptRouterModule"]]
        })
    ], HomeRoutingModule);
    return HomeRoutingModule;
}());



/***/ }),

/***/ "./home/home.component.css":
/***/ (function(module, exports) {

module.exports = ".home-panel{\r\n    vertical-align: center; \r\n    font-size: 20;\r\n    margin: 15;\r\n}\r\n\r\n.description-label{\r\n    margin-bottom: 15;\r\n}"

/***/ }),

/***/ "./home/home.component.html":
/***/ (function(module, exports) {

module.exports = "<ActionBar title=\"Home\" class=\"action-bar\">\r\n</ActionBar>\r\n<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\r\n<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\r\n<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\r\n<GridLayout>\r\n    <ScrollView class=\"page\">\r\n        <StackLayout class=\"home-panel\">\r\n            <!--Add your page content here-->\r\n            <Label textWrap=\"true\" text=\"Play with NativeScript!\" class=\"h2 description-label\"></Label>\r\n            <Label textWrap=\"true\" text=\"Write code in the editor or drag and drop components  build a NativeScript mobile application.\"\r\n                class=\"h5 description-label\"></Label>\r\n            <Label textWrap=\"true\" text=\"Scan the QR code with your mobile device and watch the changes sync live while you play with the code.\"\r\n                class=\"h2 description-label\"></Label>\r\n            <div *ngFor=\"let perg of perguntas, let i = index\">\r\n                <Label textWrap=\"true\" text={{perguntas[i]}} class=\"h4 description-label\"></Label>\r\n                <TextField [(ngModel)]=\"textFieldValue[i]\" hint=\"Enter text...\">a</TextField>\r\n            </div>\r\n            <Button text=\"2º campo console\" (tap)=\"logger(textFieldValue[1])\"></Button>\r\n\t\t\t<Button class=\"btn btn-primary btn-rounded-lg\" text=\"Large rounded corners\"></Button>\r\n\t\t\t<RadioGroup [(value)]=\"dataBoundVariable\">\r\n\t\t\t\t<RadioButton text=\"Selection 1\"></RadioButton>\r\n\t\t\t\t<RadioButton text=\"Selection 2\"></RadioButton>\r\n\t\t\t\t<RadioButton text=\"Selection 3\"></RadioButton>\r\n\t\t\t</RadioGroup>\r\n\t\t\tStackLayout>\r\n            <HtmlView [html]=\"htmlString\"></HtmlView>\r\n        </StackLayout>\r\n    </ScrollView>\r\n</GridLayout>"

/***/ }),

/***/ "./home/home.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomeComponent", function() { return HomeComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__("@angular/core");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_angular_core__WEBPACK_IMPORTED_MODULE_0__);

var HomeComponent = /** @class */ (function () {
    function HomeComponent() {
        this.htmlString = "<span><h1>HtmlView demo in <font color=\"blue\">NativeScript</font> App</h1></span>";
        this.textFieldValue = [];
        this.perguntas = ['idade', 'nome', 'rg'];
    }
    HomeComponent.prototype.onButtonTap = function () {
        console.log("Button was pressed");
    };
    HomeComponent.prototype.ngOnInit = function () {
    };
    HomeComponent.prototype.logger = function (palavra) {
        console.log(palavra);
    };
    HomeComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: "Home",
            template: __webpack_require__("./home/home.component.html"),
            styles: [__webpack_require__("./home/home.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], HomeComponent);
    return HomeComponent;
}());



/***/ }),

/***/ "./home/home.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* WEBPACK VAR INJECTION */(function(global) {/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "HomeModule", function() { return HomeModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__("@angular/core");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_angular_core__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var nativescript_angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__("nativescript-angular/common");
/* harmony import */ var nativescript_angular_common__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(nativescript_angular_common__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var nativescript_ui_sidedrawer_angular__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__("nativescript-ui-sidedrawer/angular");
/* harmony import */ var nativescript_ui_sidedrawer_angular__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(nativescript_ui_sidedrawer_angular__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var nativescript_ui_listview_angular__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__("nativescript-ui-listview/angular");
/* harmony import */ var nativescript_ui_listview_angular__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(nativescript_ui_listview_angular__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var nativescript_ui_calendar_angular__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__("nativescript-ui-calendar/angular");
/* harmony import */ var nativescript_ui_calendar_angular__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(nativescript_ui_calendar_angular__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var nativescript_ui_chart_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__("nativescript-ui-chart/angular");
/* harmony import */ var nativescript_ui_chart_angular__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(nativescript_ui_chart_angular__WEBPACK_IMPORTED_MODULE_5__);
/* harmony import */ var nativescript_ui_dataform_angular__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__("nativescript-ui-dataform/angular");
/* harmony import */ var nativescript_ui_dataform_angular__WEBPACK_IMPORTED_MODULE_6___default = /*#__PURE__*/__webpack_require__.n(nativescript_ui_dataform_angular__WEBPACK_IMPORTED_MODULE_6__);
/* harmony import */ var nativescript_ui_autocomplete_angular__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__("nativescript-ui-autocomplete/angular");
/* harmony import */ var nativescript_ui_autocomplete_angular__WEBPACK_IMPORTED_MODULE_7___default = /*#__PURE__*/__webpack_require__.n(nativescript_ui_autocomplete_angular__WEBPACK_IMPORTED_MODULE_7__);
/* harmony import */ var nativescript_ui_gauge_angular__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__("nativescript-ui-gauge/angular");
/* harmony import */ var nativescript_ui_gauge_angular__WEBPACK_IMPORTED_MODULE_8___default = /*#__PURE__*/__webpack_require__.n(nativescript_ui_gauge_angular__WEBPACK_IMPORTED_MODULE_8__);
/* harmony import */ var nativescript_angular_forms__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__("nativescript-angular/forms");
/* harmony import */ var nativescript_angular_forms__WEBPACK_IMPORTED_MODULE_9___default = /*#__PURE__*/__webpack_require__.n(nativescript_angular_forms__WEBPACK_IMPORTED_MODULE_9__);
/* harmony import */ var _home_routing_module__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__("./home/home-routing.module.ts");
/* harmony import */ var _home_component__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__("./home/home.component.ts");












var HomeModule = /** @class */ (function () {
    function HomeModule() {
    }
    HomeModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                nativescript_ui_sidedrawer_angular__WEBPACK_IMPORTED_MODULE_2__["NativeScriptUISideDrawerModule"],
                nativescript_ui_listview_angular__WEBPACK_IMPORTED_MODULE_3__["NativeScriptUIListViewModule"],
                nativescript_ui_calendar_angular__WEBPACK_IMPORTED_MODULE_4__["NativeScriptUICalendarModule"],
                nativescript_ui_chart_angular__WEBPACK_IMPORTED_MODULE_5__["NativeScriptUIChartModule"],
                nativescript_ui_dataform_angular__WEBPACK_IMPORTED_MODULE_6__["NativeScriptUIDataFormModule"],
                nativescript_ui_autocomplete_angular__WEBPACK_IMPORTED_MODULE_7__["NativeScriptUIAutoCompleteTextViewModule"],
                nativescript_ui_gauge_angular__WEBPACK_IMPORTED_MODULE_8__["NativeScriptUIGaugeModule"],
                nativescript_angular_common__WEBPACK_IMPORTED_MODULE_1__["NativeScriptCommonModule"],
                _home_routing_module__WEBPACK_IMPORTED_MODULE_10__["HomeRoutingModule"],
                nativescript_angular_forms__WEBPACK_IMPORTED_MODULE_9__["NativeScriptFormsModule"]
            ],
            declarations: [
                _home_component__WEBPACK_IMPORTED_MODULE_11__["HomeComponent"]
            ],
            schemas: [
                _angular_core__WEBPACK_IMPORTED_MODULE_0__["NO_ERRORS_SCHEMA"]
            ]
        })
    ], HomeModule);
    return HomeModule;
}());

;
    if (true) {
        module.hot.accept();
        module.hot.dispose(() => {
            // Currently the context is needed only for application style modules.
            const moduleContext = {};
            global.hmrRefresh(moduleContext);
        });
    }

/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__("../node_modules/webpack/buildin/global.js")))

/***/ })

}]);
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9ob21lL2hvbWUtcm91dGluZy5tb2R1bGUudHMiLCJ3ZWJwYWNrOi8vLy4vaG9tZS9ob21lLmNvbXBvbmVudC5jc3MiLCJ3ZWJwYWNrOi8vLy4vaG9tZS9ob21lLmNvbXBvbmVudC5odG1sIiwid2VicGFjazovLy8uL2hvbWUvaG9tZS5jb21wb25lbnQudHMiLCJ3ZWJwYWNrOi8vLy4vaG9tZS9ob21lLm1vZHVsZS50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOzs7Ozs7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUF5QztBQUU4QjtBQUV0QjtBQUVqRCxJQUFNLE1BQU0sR0FBVztJQUNuQixFQUFFLElBQUksRUFBRSxFQUFFLEVBQUUsU0FBUyxFQUFFLDZEQUFhLEVBQUU7Q0FDekMsQ0FBQztBQU1GO0lBQUE7SUFBaUMsQ0FBQztJQUFyQixpQkFBaUI7UUFKN0IsOERBQVEsQ0FBQztZQUNOLE9BQU8sRUFBRSxDQUFDLG9GQUF3QixDQUFDLFFBQVEsQ0FBQyxNQUFNLENBQUMsQ0FBQztZQUNwRCxPQUFPLEVBQUUsQ0FBQyxvRkFBd0IsQ0FBQztTQUN0QyxDQUFDO09BQ1csaUJBQWlCLENBQUk7SUFBRCx3QkFBQztDQUFBO0FBQUo7Ozs7Ozs7O0FDZDlCLDhCQUE4QiwrQkFBK0IsdUJBQXVCLG1CQUFtQixLQUFLLDJCQUEyQiwwQkFBMEIsS0FBSyxDOzs7Ozs7O0FDQXRLLG1yREFBbXJELGNBQWMsd3ZCOzs7Ozs7OztBQ0Fqc0Q7QUFBQTtBQUFBO0FBQUE7QUFBa0Q7QUFRbEQ7SUFhSTtRQVJBLGVBQVUsR0FBVyxxRkFBcUYsQ0FBQztRQUUzRyxtQkFBYyxHQUFhLEVBQUUsQ0FBQztRQUU5QixjQUFTLEdBQWEsQ0FBQyxPQUFPLEVBQUUsTUFBTSxFQUFFLElBQUksQ0FBQyxDQUFDO0lBSzlDLENBQUM7SUFiRCxtQ0FBVyxHQUFYO1FBQ0ksT0FBTyxDQUFDLEdBQUcsQ0FBQyxvQkFBb0IsQ0FBQyxDQUFDO0lBQ3RDLENBQUM7SUFhRCxnQ0FBUSxHQUFSO0lBQ0EsQ0FBQztJQUVELDhCQUFNLEdBQU4sVUFBTyxPQUFlO1FBQ2xCLE9BQU8sQ0FBQyxHQUFHLENBQUMsT0FBTyxDQUFDLENBQUM7SUFDekIsQ0FBQztJQXJCUSxhQUFhO1FBTnpCLCtEQUFTLENBQUM7WUFDUCxRQUFRLEVBQUUsTUFBTTtZQUVoQiwyREFBb0M7O1NBRXZDLENBQUM7O09BQ1csYUFBYSxDQXNCekI7SUFBRCxvQkFBQztDQUFBO0FBdEJ5Qjs7Ozs7Ozs7O0FDUjFCO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUEyRDtBQUNZO0FBQ2E7QUFDSjtBQUNBO0FBQ047QUFDTTtBQUNnQjtBQUN0QjtBQUNMO0FBRVg7QUFDVDtBQXNCakQ7SUFBQTtJQUEwQixDQUFDO0lBQWQsVUFBVTtRQXBCdEIsOERBQVEsQ0FBQztZQUNOLE9BQU8sRUFBRTtnQkFDTCxpR0FBOEI7Z0JBQzlCLDZGQUE0QjtnQkFDNUIsNkZBQTRCO2dCQUM1Qix1RkFBeUI7Z0JBQ3pCLDZGQUE0QjtnQkFDNUIsNkdBQXdDO2dCQUN4Qyx1RkFBeUI7Z0JBQ3pCLG9GQUF3QjtnQkFDeEIsdUVBQWlCO2dCQUNqQixrRkFBdUI7YUFDMUI7WUFDRCxZQUFZLEVBQUU7Z0JBQ1YsOERBQWE7YUFDaEI7WUFDRCxPQUFPLEVBQUU7Z0JBQ0wsOERBQWdCO2FBQ25CO1NBQ0osQ0FBQztPQUNXLFVBQVUsQ0FBSTtJQUFELGlCQUFDO0NBQUE7QUFBSiIsImZpbGUiOiIwLmpzIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgTmdNb2R1bGUgfSBmcm9tIFwiQGFuZ3VsYXIvY29yZVwiO1xyXG5pbXBvcnQgeyBSb3V0ZXMgfSBmcm9tIFwiQGFuZ3VsYXIvcm91dGVyXCI7XHJcbmltcG9ydCB7IE5hdGl2ZVNjcmlwdFJvdXRlck1vZHVsZSB9IGZyb20gXCJuYXRpdmVzY3JpcHQtYW5ndWxhci9yb3V0ZXJcIjtcclxuXHJcbmltcG9ydCB7IEhvbWVDb21wb25lbnQgfSBmcm9tIFwiLi9ob21lLmNvbXBvbmVudFwiO1xyXG5cclxuY29uc3Qgcm91dGVzOiBSb3V0ZXMgPSBbXHJcbiAgICB7IHBhdGg6IFwiXCIsIGNvbXBvbmVudDogSG9tZUNvbXBvbmVudCB9XHJcbl07XHJcblxyXG5ATmdNb2R1bGUoe1xyXG4gICAgaW1wb3J0czogW05hdGl2ZVNjcmlwdFJvdXRlck1vZHVsZS5mb3JDaGlsZChyb3V0ZXMpXSxcclxuICAgIGV4cG9ydHM6IFtOYXRpdmVTY3JpcHRSb3V0ZXJNb2R1bGVdXHJcbn0pXHJcbmV4cG9ydCBjbGFzcyBIb21lUm91dGluZ01vZHVsZSB7IH1cclxuIiwibW9kdWxlLmV4cG9ydHMgPSBcIi5ob21lLXBhbmVse1xcclxcbiAgICB2ZXJ0aWNhbC1hbGlnbjogY2VudGVyOyBcXHJcXG4gICAgZm9udC1zaXplOiAyMDtcXHJcXG4gICAgbWFyZ2luOiAxNTtcXHJcXG59XFxyXFxuXFxyXFxuLmRlc2NyaXB0aW9uLWxhYmVse1xcclxcbiAgICBtYXJnaW4tYm90dG9tOiAxNTtcXHJcXG59XCIiLCJtb2R1bGUuZXhwb3J0cyA9IFwiPEFjdGlvbkJhciB0aXRsZT1cXFwiSG9tZVxcXCIgY2xhc3M9XFxcImFjdGlvbi1iYXJcXFwiPlxcclxcbjwvQWN0aW9uQmFyPlxcclxcbjxsaW5rIHJlbD1cXFwic3R5bGVzaGVldFxcXCIgaHJlZj1cXFwiaHR0cHM6Ly9zdGFja3BhdGguYm9vdHN0cmFwY2RuLmNvbS9ib290c3RyYXAvNC4zLjEvY3NzL2Jvb3RzdHJhcC5taW4uY3NzXFxcIiBpbnRlZ3JpdHk9XFxcInNoYTM4NC1nZ095UjBpWENiTVF2M1hpcG1hMzRNRCtkSC8xZlE3ODQvajZjWS9pSlRRVU9oY1dyN3g5SnZvUnhUMk1adzFUXFxcIiBjcm9zc29yaWdpbj1cXFwiYW5vbnltb3VzXFxcIj5cXHJcXG48c2NyaXB0IHNyYz1cXFwiaHR0cHM6Ly9zdGFja3BhdGguYm9vdHN0cmFwY2RuLmNvbS9ib290c3RyYXAvNC4zLjEvanMvYm9vdHN0cmFwLm1pbi5qc1xcXCIgaW50ZWdyaXR5PVxcXCJzaGEzODQtSmpTbVZneWQwcDNwWEIxclJpYlpVQVlvSUl5Nk9yUTZWcmpJRWFGZi9uSkd6SXhGRHNmNHgweElNK0IwN2pSTVxcXCIgY3Jvc3NvcmlnaW49XFxcImFub255bW91c1xcXCI+PC9zY3JpcHQ+XFxyXFxuPHNjcmlwdCBzcmM9XFxcImh0dHBzOi8vY29kZS5qcXVlcnkuY29tL2pxdWVyeS0zLjMuMS5zbGltLm1pbi5qc1xcXCIgaW50ZWdyaXR5PVxcXCJzaGEzODQtcThpL1grOTY1RHpPMHJUN2FiSzQxSlN0UUlBcVZnUlZ6cGJ6bzVzbVhLcDRZZlJ2SCs4YWJ0VEUxUGk2aml6b1xcXCIgY3Jvc3NvcmlnaW49XFxcImFub255bW91c1xcXCI+PC9zY3JpcHQ+XFxyXFxuPHNjcmlwdCBzcmM9XFxcImh0dHBzOi8vY2RuanMuY2xvdWRmbGFyZS5jb20vYWpheC9saWJzL3BvcHBlci5qcy8xLjE0LjcvdW1kL3BvcHBlci5taW4uanNcXFwiIGludGVncml0eT1cXFwic2hhMzg0LVVPMmVUMENwSHFkU0pRNmhKdHk1S1ZwaHRQaHpXajlXTzFjbEhUTUdhM0pEWndyblFxNHNGODZkSUhORHowVzFcXFwiIGNyb3Nzb3JpZ2luPVxcXCJhbm9ueW1vdXNcXFwiPjwvc2NyaXB0PlxcclxcbjxHcmlkTGF5b3V0PlxcclxcbiAgICA8U2Nyb2xsVmlldyBjbGFzcz1cXFwicGFnZVxcXCI+XFxyXFxuICAgICAgICA8U3RhY2tMYXlvdXQgY2xhc3M9XFxcImhvbWUtcGFuZWxcXFwiPlxcclxcbiAgICAgICAgICAgIDwhLS1BZGQgeW91ciBwYWdlIGNvbnRlbnQgaGVyZS0tPlxcclxcbiAgICAgICAgICAgIDxMYWJlbCB0ZXh0V3JhcD1cXFwidHJ1ZVxcXCIgdGV4dD1cXFwiUGxheSB3aXRoIE5hdGl2ZVNjcmlwdCFcXFwiIGNsYXNzPVxcXCJoMiBkZXNjcmlwdGlvbi1sYWJlbFxcXCI+PC9MYWJlbD5cXHJcXG4gICAgICAgICAgICA8TGFiZWwgdGV4dFdyYXA9XFxcInRydWVcXFwiIHRleHQ9XFxcIldyaXRlIGNvZGUgaW4gdGhlIGVkaXRvciBvciBkcmFnIGFuZCBkcm9wIGNvbXBvbmVudHMgIGJ1aWxkIGEgTmF0aXZlU2NyaXB0IG1vYmlsZSBhcHBsaWNhdGlvbi5cXFwiXFxyXFxuICAgICAgICAgICAgICAgIGNsYXNzPVxcXCJoNSBkZXNjcmlwdGlvbi1sYWJlbFxcXCI+PC9MYWJlbD5cXHJcXG4gICAgICAgICAgICA8TGFiZWwgdGV4dFdyYXA9XFxcInRydWVcXFwiIHRleHQ9XFxcIlNjYW4gdGhlIFFSIGNvZGUgd2l0aCB5b3VyIG1vYmlsZSBkZXZpY2UgYW5kIHdhdGNoIHRoZSBjaGFuZ2VzIHN5bmMgbGl2ZSB3aGlsZSB5b3UgcGxheSB3aXRoIHRoZSBjb2RlLlxcXCJcXHJcXG4gICAgICAgICAgICAgICAgY2xhc3M9XFxcImgyIGRlc2NyaXB0aW9uLWxhYmVsXFxcIj48L0xhYmVsPlxcclxcbiAgICAgICAgICAgIDxkaXYgKm5nRm9yPVxcXCJsZXQgcGVyZyBvZiBwZXJndW50YXMsIGxldCBpID0gaW5kZXhcXFwiPlxcclxcbiAgICAgICAgICAgICAgICA8TGFiZWwgdGV4dFdyYXA9XFxcInRydWVcXFwiIHRleHQ9e3twZXJndW50YXNbaV19fSBjbGFzcz1cXFwiaDQgZGVzY3JpcHRpb24tbGFiZWxcXFwiPjwvTGFiZWw+XFxyXFxuICAgICAgICAgICAgICAgIDxUZXh0RmllbGQgWyhuZ01vZGVsKV09XFxcInRleHRGaWVsZFZhbHVlW2ldXFxcIiBoaW50PVxcXCJFbnRlciB0ZXh0Li4uXFxcIj5hPC9UZXh0RmllbGQ+XFxyXFxuICAgICAgICAgICAgPC9kaXY+XFxyXFxuICAgICAgICAgICAgPEJ1dHRvbiB0ZXh0PVxcXCIywrogY2FtcG8gY29uc29sZVxcXCIgKHRhcCk9XFxcImxvZ2dlcih0ZXh0RmllbGRWYWx1ZVsxXSlcXFwiPjwvQnV0dG9uPlxcclxcblxcdFxcdFxcdDxCdXR0b24gY2xhc3M9XFxcImJ0biBidG4tcHJpbWFyeSBidG4tcm91bmRlZC1sZ1xcXCIgdGV4dD1cXFwiTGFyZ2Ugcm91bmRlZCBjb3JuZXJzXFxcIj48L0J1dHRvbj5cXHJcXG5cXHRcXHRcXHQ8UmFkaW9Hcm91cCBbKHZhbHVlKV09XFxcImRhdGFCb3VuZFZhcmlhYmxlXFxcIj5cXHJcXG5cXHRcXHRcXHRcXHQ8UmFkaW9CdXR0b24gdGV4dD1cXFwiU2VsZWN0aW9uIDFcXFwiPjwvUmFkaW9CdXR0b24+XFxyXFxuXFx0XFx0XFx0XFx0PFJhZGlvQnV0dG9uIHRleHQ9XFxcIlNlbGVjdGlvbiAyXFxcIj48L1JhZGlvQnV0dG9uPlxcclxcblxcdFxcdFxcdFxcdDxSYWRpb0J1dHRvbiB0ZXh0PVxcXCJTZWxlY3Rpb24gM1xcXCI+PC9SYWRpb0J1dHRvbj5cXHJcXG5cXHRcXHRcXHQ8L1JhZGlvR3JvdXA+XFxyXFxuXFx0XFx0XFx0U3RhY2tMYXlvdXQ+XFxyXFxuICAgICAgICAgICAgPEh0bWxWaWV3IFtodG1sXT1cXFwiaHRtbFN0cmluZ1xcXCI+PC9IdG1sVmlldz5cXHJcXG4gICAgICAgIDwvU3RhY2tMYXlvdXQ+XFxyXFxuICAgIDwvU2Nyb2xsVmlldz5cXHJcXG48L0dyaWRMYXlvdXQ+XCIiLCJpbXBvcnQgeyBDb21wb25lbnQsIE9uSW5pdCB9IGZyb20gXCJAYW5ndWxhci9jb3JlXCI7XHJcblxyXG5AQ29tcG9uZW50KHtcclxuICAgIHNlbGVjdG9yOiBcIkhvbWVcIixcclxuICAgIG1vZHVsZUlkOiBtb2R1bGUuaWQsXHJcbiAgICB0ZW1wbGF0ZVVybDogXCIuL2hvbWUuY29tcG9uZW50Lmh0bWxcIixcclxuICAgIHN0eWxlVXJsczogW1wiLi9ob21lLmNvbXBvbmVudC5jc3NcIl1cclxufSlcclxuZXhwb3J0IGNsYXNzIEhvbWVDb21wb25lbnQgaW1wbGVtZW50cyBPbkluaXQge1xyXG4gICAgb25CdXR0b25UYXAoKTogdm9pZCB7XHJcbiAgICAgICAgY29uc29sZS5sb2coXCJCdXR0b24gd2FzIHByZXNzZWRcIik7XHJcbiAgICB9XHJcblxyXG4gICAgaHRtbFN0cmluZzogc3RyaW5nID0gXCI8c3Bhbj48aDE+SHRtbFZpZXcgZGVtbyBpbiA8Zm9udCBjb2xvcj1cXFwiYmx1ZVxcXCI+TmF0aXZlU2NyaXB0PC9mb250PiBBcHA8L2gxPjwvc3Bhbj5cIjtcclxuXHJcbiAgICB0ZXh0RmllbGRWYWx1ZTogc3RyaW5nW10gPSBbXTtcclxuXHJcbiAgICBwZXJndW50YXM6IHN0cmluZ1tdID0gWydpZGFkZScsICdub21lJywgJ3JnJ107XHJcblxyXG5cclxuXHJcbiAgICBjb25zdHJ1Y3RvcigpIHtcclxuICAgIH1cclxuXHJcbiAgICBuZ09uSW5pdCgpOiB2b2lkIHtcclxuICAgIH1cclxuXHJcbiAgICBsb2dnZXIocGFsYXZyYTogc3RyaW5nKTogdm9pZHtcclxuICAgICAgICBjb25zb2xlLmxvZyhwYWxhdnJhKTtcclxuICAgIH1cclxufVxyXG4iLCJpbXBvcnQgeyBOZ01vZHVsZSwgTk9fRVJST1JTX1NDSEVNQSB9IGZyb20gXCJAYW5ndWxhci9jb3JlXCI7XHJcbmltcG9ydCB7IE5hdGl2ZVNjcmlwdENvbW1vbk1vZHVsZSB9IGZyb20gXCJuYXRpdmVzY3JpcHQtYW5ndWxhci9jb21tb25cIjtcclxuaW1wb3J0IHsgTmF0aXZlU2NyaXB0VUlTaWRlRHJhd2VyTW9kdWxlIH0gZnJvbSBcIm5hdGl2ZXNjcmlwdC11aS1zaWRlZHJhd2VyL2FuZ3VsYXJcIjtcclxuaW1wb3J0IHsgTmF0aXZlU2NyaXB0VUlMaXN0Vmlld01vZHVsZSB9IGZyb20gXCJuYXRpdmVzY3JpcHQtdWktbGlzdHZpZXcvYW5ndWxhclwiO1xyXG5pbXBvcnQgeyBOYXRpdmVTY3JpcHRVSUNhbGVuZGFyTW9kdWxlIH0gZnJvbSBcIm5hdGl2ZXNjcmlwdC11aS1jYWxlbmRhci9hbmd1bGFyXCI7XHJcbmltcG9ydCB7IE5hdGl2ZVNjcmlwdFVJQ2hhcnRNb2R1bGUgfSBmcm9tIFwibmF0aXZlc2NyaXB0LXVpLWNoYXJ0L2FuZ3VsYXJcIjtcclxuaW1wb3J0IHsgTmF0aXZlU2NyaXB0VUlEYXRhRm9ybU1vZHVsZSB9IGZyb20gXCJuYXRpdmVzY3JpcHQtdWktZGF0YWZvcm0vYW5ndWxhclwiO1xyXG5pbXBvcnQgeyBOYXRpdmVTY3JpcHRVSUF1dG9Db21wbGV0ZVRleHRWaWV3TW9kdWxlIH0gZnJvbSBcIm5hdGl2ZXNjcmlwdC11aS1hdXRvY29tcGxldGUvYW5ndWxhclwiO1xyXG5pbXBvcnQgeyBOYXRpdmVTY3JpcHRVSUdhdWdlTW9kdWxlIH0gZnJvbSBcIm5hdGl2ZXNjcmlwdC11aS1nYXVnZS9hbmd1bGFyXCI7XHJcbmltcG9ydCB7IE5hdGl2ZVNjcmlwdEZvcm1zTW9kdWxlIH0gZnJvbSBcIm5hdGl2ZXNjcmlwdC1hbmd1bGFyL2Zvcm1zXCI7XHJcblxyXG5pbXBvcnQgeyBIb21lUm91dGluZ01vZHVsZSB9IGZyb20gXCIuL2hvbWUtcm91dGluZy5tb2R1bGVcIjtcclxuaW1wb3J0IHsgSG9tZUNvbXBvbmVudCB9IGZyb20gXCIuL2hvbWUuY29tcG9uZW50XCI7XHJcblxyXG5ATmdNb2R1bGUoe1xyXG4gICAgaW1wb3J0czogW1xyXG4gICAgICAgIE5hdGl2ZVNjcmlwdFVJU2lkZURyYXdlck1vZHVsZSxcclxuICAgICAgICBOYXRpdmVTY3JpcHRVSUxpc3RWaWV3TW9kdWxlLFxyXG4gICAgICAgIE5hdGl2ZVNjcmlwdFVJQ2FsZW5kYXJNb2R1bGUsXHJcbiAgICAgICAgTmF0aXZlU2NyaXB0VUlDaGFydE1vZHVsZSxcclxuICAgICAgICBOYXRpdmVTY3JpcHRVSURhdGFGb3JtTW9kdWxlLFxyXG4gICAgICAgIE5hdGl2ZVNjcmlwdFVJQXV0b0NvbXBsZXRlVGV4dFZpZXdNb2R1bGUsXHJcbiAgICAgICAgTmF0aXZlU2NyaXB0VUlHYXVnZU1vZHVsZSxcclxuICAgICAgICBOYXRpdmVTY3JpcHRDb21tb25Nb2R1bGUsXHJcbiAgICAgICAgSG9tZVJvdXRpbmdNb2R1bGUsXHJcbiAgICAgICAgTmF0aXZlU2NyaXB0Rm9ybXNNb2R1bGVcclxuICAgIF0sXHJcbiAgICBkZWNsYXJhdGlvbnM6IFtcclxuICAgICAgICBIb21lQ29tcG9uZW50XHJcbiAgICBdLFxyXG4gICAgc2NoZW1hczogW1xyXG4gICAgICAgIE5PX0VSUk9SU19TQ0hFTUFcclxuICAgIF1cclxufSlcclxuZXhwb3J0IGNsYXNzIEhvbWVNb2R1bGUgeyB9XHJcbiJdLCJzb3VyY2VSb290IjoiIn0=