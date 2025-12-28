
export interface TransactionModule {
  id?: number;
  dateOp?: Date;
  montant?: number;
  type: 'CREDIT' | 'DEBIT';
  idCompteDestin?:number;
  description?:string;

}


