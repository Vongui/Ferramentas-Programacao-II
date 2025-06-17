export interface Usuario {
    codigo: number,
    nomeCompleto: string,
    email: string,
    nomeLogin: string,
    permissoes: string[],
    tokenJWT: string
}