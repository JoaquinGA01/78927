package mx.uv.t4is.SaludosBd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludosdb.SaludarResponse;
import https.t4is_uv_mx.saludosdb.BuscarSaludosResponse.Saludos;
import https.t4is_uv_mx.saludosdb.BorrarSaludoRequest;
import https.t4is_uv_mx.saludosdb.BorrarSaludoResponse;
import https.t4is_uv_mx.saludosdb.BuscarSaludosIdRequest;
import https.t4is_uv_mx.saludosdb.BuscarSaludosIdResponse;
import https.t4is_uv_mx.saludosdb.BuscarSaludosResponse;
import https.t4is_uv_mx.saludosdb.ModificarSaludoRequest;
import https.t4is_uv_mx.saludosdb.ModificarSaludoResponse;
import https.t4is_uv_mx.saludosdb.SaludarRequest;



@Endpoint
public class SaludosEndPoint {
    //int i=1;
    //private List<BuscarSaludosResponse.Saludos> saludos = new ArrayList<BuscarSaludosResponse.Saludos>();
    @Autowired
    private Isaludadores isaludadores; 
    
    @PayloadRoot(namespace = "https://t4is.uv.mx/SaludosDb", localPart = "SaludarRequest")
    @ResponsePayload
    public SaludarResponse saludar(@RequestPayload SaludarRequest nombre){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Hola" + nombre.getNombre());
        Saludadores s = new Saludadores();//BuscarSaludosResponse.Saludos s = new BuscarSaludosResponse.Saludos();
        //s.setId(generarCodigo());
        s.setNombre(nombre.getNombre());
        isaludadores.save(s); //saludos.add(s);
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/SaludosDb", localPart = "BuscarSaludosRequest")
    @ResponsePayload
    public BuscarSaludosResponse buscar(){
        BuscarSaludosResponse buscarSaludosResponse = new BuscarSaludosResponse();
        /*for(int i = 0; i<saludos.size();i++){
            buscarSaludosResponse.getSaludos().add(saludos.get(i));
        }*/

        List<Saludadores> listsaludadores = (List<Saludadores>) isaludadores.findAll();
        Saludos saludo = new Saludos();
        for(int i=0;i<listsaludadores.size();i++){
            saludo = new Saludos();
            saludo.setId(listsaludadores.get(i).getId());
            saludo.setNombre(listsaludadores.get(i).getNombre());
            buscarSaludosResponse.getSaludos().add(saludo);
        }
        return buscarSaludosResponse;
    }


    @PayloadRoot(namespace = "https://t4is.uv.mx/SaludosDb", localPart = "BuscarSaludosIdRequest")
    @ResponsePayload
    public BuscarSaludosIdResponse buscar(@RequestPayload BuscarSaludosIdRequest id){
        BuscarSaludosIdResponse buscarSaludosResponse = new BuscarSaludosIdResponse();
        /*for(int i = 0; i<saludos.size();i++){
            buscarSaludosResponse.getSaludos().add(saludos.get(i));
        }*/
        //buscarSaludosResponse.getSaludos().add(isaludadores.findAll());
        BuscarSaludosIdResponse.Saludos s = new BuscarSaludosIdResponse.Saludos();
        Saludadores saludadores = isaludadores.findById(id.getId()).get();
        s.setId(saludadores.getId());
        s.setNombre(saludadores.getNombre());
        buscarSaludosResponse.getSaludos().add(s);
        return buscarSaludosResponse;
    }


    @PayloadRoot(namespace = "https://t4is.uv.mx/SaludosDb", localPart="ModificarSaludoRequest")
    @ResponsePayload
    public ModificarSaludoResponse modificar(@RequestPayload ModificarSaludoRequest peticion){
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        Saludadores e = new Saludadores();

        e.setNombre(peticion.getNombre());
        e.setId(peticion.getId());
        //saludos.set(peticion.getId()-1, e);
        isaludadores.save(e);
        respuesta.setRespuesta(true);
        return respuesta;
    }

    @PayloadRoot(namespace = "https://t4is.uv.mx/SaludosDb", localPart="BorrarSaludoRequest")
    @ResponsePayload
    public BorrarSaludoResponse modificar(@RequestPayload BorrarSaludoRequest peticion){
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();
        //BuscarSaludosResponse.Saludos e = new BuscarSaludosResponse.Saludos();
        isaludadores.deleteById(peticion.getId());
        //e.setId(peticion.getId());
        //saludos.remove(peticion.getId()-1);
        respuesta.setRespuesta(true);
        /*i=1;
        for(int j = 0; j<saludos.size();j++){
            saludos.get(j).setId(j+1);
            i++;
        }*/
        return respuesta;
    }

}