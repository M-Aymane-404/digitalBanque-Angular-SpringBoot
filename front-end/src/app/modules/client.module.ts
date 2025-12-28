export interface Client {
  id?: number;
  nom?: string;
  prenom?: string;
  email?: string;
  compteBancaireids?: Number[];
  accIDs?: String[];


  nbCompte?:number;
  soldeTotale?:number;
}
