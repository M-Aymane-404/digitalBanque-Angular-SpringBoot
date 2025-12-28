import { Routes } from '@angular/router';
import {Dashboard} from './dashboard/dashboard';

 import {Settings} from './settings/settings';

 import {Clients} from './Client/clients/clients';
import {NewClient} from './Client/new-client/new-client';
import {EditClient} from './Client/edit-client/edit-client';
import {Accounts} from './BanqueAccount/accounts/accounts';
import {NewAccount} from './BanqueAccount/new-account/new-account';
import {EditAccount} from './BanqueAccount/edit-account/edit-account';
import {NewTransaction} from './transactions/new-transaction/new-transaction';
 import {Transactions} from './transactions/transactions/transactions';
import {Login} from './login/login';
import {NavBar} from './nav-bar/nav-bar';
import {authenticationGuard} from './guards/authentication-guard';
import {NotAuthorized} from './not-authorized/not-authorized';
import {authorizationGuard} from './guards/authorization-guard';


export const routes: Routes = [




  {path:'login' , component:Login},
  {path:'' , redirectTo:'login' , pathMatch:'full'},

  {path: 'nav' , component:NavBar,canActivate:[authenticationGuard], children:[
      {path : "dashboard" , component:Dashboard},


      {path: "clients" , component:Clients},
      {path:"client/add" , component:NewClient},
      {path:"client/edit/:id" , component:EditClient,canActivate:[authorizationGuard],data:{role:"ADMIN"}},
      { path: '', redirectTo: 'clients', pathMatch: 'full' },



      {path: "accounts/:id" , component:Accounts},
      {path:"account/add/:id" , component:NewAccount},
      {path:"account/edit/:id" , component:EditAccount,canActivate:[authorizationGuard],data:{role:"ADMIN"}},

      {path: "transactions/:id" , component:Transactions},
      {path:"transaction/add/:id" , component:NewTransaction},



    ]},

  {path:"notAuthorized", component:NotAuthorized}




];
