import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Client} from '../modules/client.module';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  constructor(private http:HttpClient) {
  }


getAllClients(){
  return this.http.get<Array<Client>>("http://localhost:8080/api/clients");
}
getClientByKeyword(keyword:String){
  return this.http.get<Array<Client>>("http://localhost:8080/api/clients?keyword="+keyword);
}
getClientById(id:number){
    return this.http.get('http://localhost:8080/api/clients/'+id)

}

  UpdateClient(clientToUpdated: Client) {
    return this.http.put("http://localhost:8080/api/client/update/"+clientToUpdated.id,clientToUpdated)

  }

  createClient(client:Client){
    return this.http.post("http://localhost:8080/api/clients/add",client);
  }

    deleteClientService(client: Client){
      return this.http.delete(`http://localhost:8080/api/clients/delete/${client.id}`);

    }


}
