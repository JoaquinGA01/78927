using System;
using WSDL.mensajes;

namespace WSDL.operaciones{
    public class Operaciones : Mensajes
    {
        public string Saludar(string nombre){
            string msj = "Hola" + nombre;
            return msj;
        }
        public string Mostrar(int id){
            return "x";
        }
        public string Sentenciaif(int numero){
            if(numero>5){
            return "Yo solo";
            }else{
                return "con ayuda";
            }
        }
    }
}