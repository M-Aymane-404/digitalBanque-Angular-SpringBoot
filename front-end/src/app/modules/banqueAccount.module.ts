export interface BanqueAccountModule {
  id?:number;
  dateCreation?:Date;
  solde?:number;
  statut?:string;
  devise?:string;
  accId?:String;
  type: 'CC' | 'CE';
   nomClient?:string;
   prenomClient?:string;
   idClient?:number;
  decouvert?:number;
  tauxInteret?:number;
}
