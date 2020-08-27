import {Role} from './role.model';

export class User {
  idUser: number;
  username: string;
  password: string;
  enabled: boolean;
  roles: Role[];
}
