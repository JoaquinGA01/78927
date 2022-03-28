package mx.uv.TIS.saludos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.tis_uv_mx.saludos.SaludarResponse;
import https.tis_uv_mx.saludos.BorrarSaludoRequest;
import https.tis_uv_mx.saludos.BorrarSaludoResponse;
import https.tis_uv_mx.saludos.BuscarSaludosResponse;
import https.tis_uv_mx.saludos.ModificarSaludoRequest;
import https.tis_uv_mx.saludos.ModificarSaludoResponse;
import https.tis_uv_mx.saludos.SaludarRequest;



@Endpoint
public class SaludosEndPoint {
    int i=1;
    private List<BuscarSaludosResponse.Saludos> saludos = new ArrayList<BuscarSaludosResponse.Saludos>();
    @PayloadRoot(namespace = "https://TIS.uv.mx/saludos", localPart = "SaludarRequest")

    @ResponsePayload
    public SaludarResponse saludar(@RequestPayload SaludarRequest nombre){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Hola" + nombre.getNombre());
        BuscarSaludosResponse.Saludos s = new BuscarSaludosResponse.Saludos();
        s.setId(generarCodigo());
        s.setNombre(respuesta.getRespuesta());
        saludos.add(s);
        return respuesta;
    }


    @PayloadRoot(namespace = "https://TIS.uv.mx/saludos", localPart = "BuscarSaludosRequest")
    @ResponsePayload
    public BuscarSaludosResponse buscar(){
        BuscarSaludosResponse buscarSaludosResponse = new BuscarSaludosResponse();
        for(int i = 0; i<saludos.size();i++){
            buscarSaludosResponse.getSaludos().add(saludos.get(i));
        }
        return buscarSaludosResponse;
    }


    @PayloadRoot(namespace = "https://TIS.uv.mx/saludos", localPart="ModificarSaludoRequest")
    @ResponsePayload
    public ModificarSaludoResponse modificar(@RequestPayload ModificarSaludoRequest peticion){
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        BuscarSaludosResponse.Saludos e = new BuscarSaludosResponse.Saludos();
        e.setNombre(peticion.getNombre());
        e.setId(peticion.getId());
        saludos.set(peticion.getId()-1, e);
        respuesta.setRespuesta(true);
        return respuesta;
    }

    @PayloadRoot(namespace = "https://TIS.uv.mx/saludos", localPart="BorrarSaludoRequest")
    @ResponsePayload
    public BorrarSaludoResponse modificar(@RequestPayload BorrarSaludoRequest peticion){
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();
        BuscarSaludosResponse.Saludos e = new BuscarSaludosResponse.Saludos();

        e.setId(peticion.getId());
        saludos.remove(peticion.getId()-1);
        respuesta.setRespuesta(true);
        i=1;
        for(int j = 0; j<saludos.size();j++){
            saludos.get(j).setId(j+1);
            i++;
        }
        return respuesta;
    }

    public int generarCodigo(){
        return i++;
    }
}