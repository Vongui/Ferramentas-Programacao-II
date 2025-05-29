package com.prova.api.controller;

import com.prova.api.controller.dto.PacotesDTO;
import com.prova.api.controller.dto.PacotesMapper;
import com.prova.api.controller.dto.ViajanteDTO;
import com.prova.api.model.Cliente;
import com.prova.api.model.Pacotes;
import com.prova.api.model.Viajantes;
import com.prova.api.model.ViajantesId;
import com.prova.api.service.ClienteService;
import com.prova.api.service.PacotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacotes")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Pacotes Controller", description = "gerenciamento de Pacotes de Viagem")
public class PacotesController {

    private final PacotesService pacotesService;
    private final PacotesMapper pacotesMapper;
    private final ClienteService clienteService;

    @Operation(summary = "Criar Pacote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pacote criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação do Pacote")
    })
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacotesDTO> criarPacote(@RequestBody PacotesDTO pacotesDTO) throws Exception{
        Pacotes pacotes = pacotesService.create(pacotesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacotesMapper.pacoteToDto(pacotes));
    }

    @Operation(summary = "Listar todos os pacotes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pacotes retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum pacote encontrado")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PacotesDTO>> buscarPacotes(){
        List<PacotesDTO> lista = pacotesService.getAll();
        if (lista == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Buscar pacotes de um cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacotes do cliente encontrados"),
            @ApiResponse(responseCode = "404", description = "Pacotes não encontrados")
    })
    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PacotesDTO>> buscarPacotePorID(@PathVariable int codigo){

        Cliente client = clienteService.buscarPorId(codigo);
        if (client != null){
            List<Pacotes> lista = pacotesService.buscarPacoteID(client.getId());
            return ResponseEntity.ok(pacotesMapper.pacotesToDto(lista));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cancelar Pacote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacote cancelado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pacote não encontrado")
    })
    @PatchMapping(value = "/{codigo}/cancelar")
    public ResponseEntity<String> cancelarPacote(@PathVariable int codigo){
        if(pacotesService.alterarStatus(codigo, 2)){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Pagar Pacote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacote Pago com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pacote não encontrado")
    })
    @PatchMapping(value = "/{codigo}/pagar")
    public ResponseEntity<String> pagarPacote(@PathVariable int codigo){
        if(pacotesService.alterarStatus(codigo, 1)){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Incluir Viajante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viajante incluido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pacote não encontrado")
    })
    @PostMapping(value = "/{codigo}/viajante", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> incluirViajante(@PathVariable int codigo, @RequestBody ViajanteDTO v){
        if(pacotesService.verificarExistenciaPorId(codigo)){
            return ResponseEntity.ok(null);
            //Aqui dentro é pra ser passado para a classe ViajanteController para realizar o POST
        }
        return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Listar todos os viajantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de viajantes retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum viajante encontrado")
    })
    @GetMapping(value = "/{codigo}/viajante", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Viajantes>> buscarViajantes(@PathVariable int codigo){
        if(pacotesService.verificarExistenciaPorId(codigo)){
            return ResponseEntity.ok(null);

            //Aqui dentro é pra ser passado para a classe ViajanteController para realizar o GET
        }
        return ResponseEntity.notFound().build();
    }


}
