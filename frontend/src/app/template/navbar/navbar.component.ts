import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  logoPath = '/assets/img/deltagrupo-logo-color.svg';

  constructor() { }

  ngOnInit(): void {
  }

}
