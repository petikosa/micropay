import {Component} from '@angular/core';
import {TransactionsService} from '../../api/transactions/services/transactions.service';
import {Transaction} from '../../api/transactions/models/transaction';
import {AccountsService} from '../../api/transactions/services/accounts.service';
import {Account} from '../../api/transactions/models/account';

@Component({
  selector: 'app-transactions',
  imports: [],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.scss'
})
export class TransactionsComponent {

  protected transactions: Transaction = [];
  protected accounts: Account = [];

  constructor(protected transactionsService: TransactionsService,
              protected accountsService: AccountsService) {
    this.transactionsService.getAllTransactions().subscribe(response => {
      this.transactions = response;
    });
    this.accountsService.getAllAccounts().subscribe(response => {
        this.accounts = response;
      }
    )
  }

}
