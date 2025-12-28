export interface Operation {
  id: number;
  dateOp: string;
  type: 'CREDIT' | 'DEBIT';
  montant: number;
  description: string;
}
