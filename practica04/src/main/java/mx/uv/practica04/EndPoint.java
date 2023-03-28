package mx.uv.practica04;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;

@Endpoint
public class EndPoint{
    @Autowired
    private ISaludador iSaludador;

    List<String> mensaje = new ArrayList<String>();
    String nombres;

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion){
        SaludarResponse respuesta = new SaludarResponse();
        respuesta.setRespuesta("Bienvenido: "+peticion.getNombre());

        //Persistencia a la bd
        Saludador saludador=new Saludador();
        saludador.setNombre(peticion.getNombre());
        iSaludador.save(saludador);
        
        //mensaje.add(peticion.getNombre());
        return respuesta;
    }

    
}