
export interface TransactionModule {
  id?: number;
  dateOp?: Date;
  montant?: number;
  type: 'CREDIT' | 'DEBIT';
  accIdDestin?:number;
  description?:string;

}


