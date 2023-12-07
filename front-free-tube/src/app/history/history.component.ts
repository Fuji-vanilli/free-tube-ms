import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit{

  videoHistories: Array<string>= [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {

  }

}
