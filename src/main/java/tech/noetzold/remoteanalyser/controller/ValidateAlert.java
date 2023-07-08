package tech.noetzold.remoteanalyser.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.noetzold.remoteanalyser.model.Alerta;
import tech.noetzold.remoteanalyser.service.AlertaService;
import tech.noetzold.remoteanalyser.service.LoginAppService;
import tech.noetzold.remoteanalyser.util.LoginApiService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ValidateAlert {

    @Autowired
    AlertaService alertaService;

    @Autowired
    private LoginAppService loginService;

    @Autowired
    private LoginApiService loginProp;

    @GetMapping("/alertas/download/{id}")
    public ResponseEntity<byte[]> downloadAlertaJson(@PathVariable("id") Long alertaId) {
        Alerta alerta = alertaService.buscaAlertaById(loginProp.getTokenBearer(loginService.getToken(loginProp)), alertaId);

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] jsonBytes;
        try {
            jsonBytes = objectMapper.writeValueAsBytes(alerta);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        String hash = generateHash(alerta.getId(), alerta.getPcId());

        ObjectNode jsonNode = objectMapper.createObjectNode();
        try {
            jsonNode.set("alerta", objectMapper.readTree(jsonBytes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        jsonNode.put("hash", hash);

        byte[] jsonComHashBytes;
        try {
            jsonComHashBytes = objectMapper.writeValueAsBytes(jsonNode);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", "alerta.json");

        return new ResponseEntity<>(jsonComHashBytes, headers, HttpStatus.OK);
    }

    @PostMapping("/alertas/validar")
    public String validarAlerta(@RequestParam("alertaId") Long alertaId, @RequestParam("pcId") String pcId, @RequestParam("hash") String hash, Model model, @AuthenticationPrincipal UserDetails userDetails) {

        try {
            List<Alerta> alertas = alertaService.buscaAlertasPcId(loginProp.getTokenBearer(loginService.getToken(loginProp)), userDetails.getUsername());
            model.addAttribute("alertas", alertas);
        }catch (Exception e){
            model.addAttribute("alertas", new ArrayList<Alerta>());
            model.addAttribute("errorMessage", "Nenhum alerta encontrado.");
        }

        String hashAlert = generateHash(alertaId, pcId);

        if (hashAlert.equals(hash)) {
            model.addAttribute("resultadoValidacao", "O hash do Alerta é válido");
        } else {
            model.addAttribute("resultadoValidacao", "O hash do Alerta é inválido");
        }

        return "user";
    }

    public String generateHash(Long id, String pcId) {
        try {
            String input = id + pcId;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
