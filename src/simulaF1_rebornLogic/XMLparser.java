package simulaF1_rebornLogic;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;

public class XMLparser {

	


		public static void leArquivoCampeonato(String nomeArquivoCarregamento, final LogicSimulaF1_RebornChampionship campeonato) {
			try{

				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser saxParser = factory.newSAXParser();

				DefaultHandler handler = new DefaultHandler() {

					

					//Equipe equipeTemp = new Equipe();

					//PARA EQUIPE
					boolean equipe;
					boolean escuderia;
					boolean pontuacaoEquipe;
					boolean mecanico;

					String nomeEscuderia;
					int pontosEquipe;
					String nomeMecanico;

					//PARA PILOTO
					boolean piloto;
					boolean nome;
					boolean abrev;
					boolean nacionalidade;
					boolean pontuacao;

					String nomePiloto;
					String nac;
					String abreviacao;

					int pontosPiloto;

					//PARA ENGENHEIRO
					boolean engenheiro;
					boolean nomeEng;

					String nomeDoEngenheiro;

					//PARA CARRO
					boolean carro;
					boolean pneu;
					boolean combustivel;
					boolean peso;
					int numCarro;
					String cor;
					int tipoPneu;
					int litrosCombustivel;
					int pesoCarro;

					ArrayList<LogicSimulaF1_RebornPilot> pilostosTemp = new ArrayList<>();
					ArrayList<LogicSimulaF1_RebornEngineer> engenheirosTemp = new ArrayList<>();
					ArrayList<LogicSimulaF1_RebornCar> carrosTemp = new ArrayList<>();

					LogicSimulaF1_RebornMechanic mecanicoTemp;
					LogicSimulaF1_RebornCar carroTemp;
					LogicSimulaF1_RebornEngineer engTemp;
					LogicSimulaF1_RebornPilot pilotoTemp;



					public void startElement(String uri, String localName, String qName, Attributes attributes) throws org.xml.sax.SAXException {						

						//PARA EQUIPE
						if(qName.equals("equipe")) equipe = true;	
						if(qName.equals("escuderia")) escuderia = true;
						if(qName.equals("pontuacaoEquipe")) pontuacaoEquipe = true;
						if(qName.equals("mecanico")) mecanico = true;

						//PARA PILOTO
						if(qName.equals("piloto")) piloto = true;
						if(qName.equals("nome")) nome = true;
						if(qName.equals("abrev")) abrev = true;
						if(qName.equals("nacionalidade")) nacionalidade = true;
						if(qName.equals("pontuacao")) pontuacao = true;

						//PARA ENGENHEIRO
						if(qName.equals("engenheiro")) engenheiro = true;
						if(qName.equals("nomeEng")) nomeEng = true;


						//PARA CARRO
						if(qName.equals("carro")){
							carro = true;
							numCarro = Integer.parseInt(attributes.getValue("numero"));
							cor = attributes.getValue("cor");								
						}
						if(qName.equals("pneu")){
							pneu = true;
							tipoPneu = Integer.parseInt(attributes.getValue("tipo"));
						}

						if(qName.equals("combustivel")){
							combustivel = true;
							litrosCombustivel = Integer.parseInt(attributes.getValue("quantidade"));
						}

						if(qName.equals("peso")) peso = true;

					}	

					public void endElement(String uri, String localName, String qName) throws SAXException {

						if(qName.equals("equipe")){

							//CRIA A EQUIPE
							LogicSimulaF1_RebornTeam equipe = new LogicSimulaF1_RebornTeam();

							//SETA OS VALORES "equipe" QUE ESTAVA FALTANDO NOS OUTROS OBJETOS
							mecanicoTemp.setSquad(equipe);
							
							for(LogicSimulaF1_RebornEngineer eng: engenheirosTemp ) eng.setSquad(equipe);
							for(LogicSimulaF1_RebornCar car: carrosTemp) car.setSquad(equipe);
							for(LogicSimulaF1_RebornPilot piloto: pilostosTemp) piloto.setSquad(equipe);


							HashMap<String, LogicSimulaF1_RebornEngineer> hashEngenheiro = new HashMap<>();
							HashMap<String, LogicSimulaF1_RebornPilot> hashPiloto = new HashMap<>();
							HashMap<LogicSimulaF1_RebornPilot, LogicSimulaF1_RebornCar> hashCarros = new HashMap<>();						

							for(LogicSimulaF1_RebornEngineer eng: engenheirosTemp ) hashEngenheiro.put(eng.getName(), eng);		
							for(LogicSimulaF1_RebornCar car: carrosTemp)hashCarros.put(car.getPilot(), car);		
							for(LogicSimulaF1_RebornPilot piloto: pilostosTemp) hashPiloto.put(piloto.getName(), piloto);


							//SETA OS ATRIBUTOS DE EQUIPE
							equipe.setMechanic(mecanicoTemp);
							equipe.setEngineers(hashEngenheiro);
							equipe.setPilots(hashPiloto);
							equipe.setCars(hashCarros);
							equipe.setTeamName(nomeEscuderia);
							equipe.setChampionship(campeonato);


							//SETA A EQUIPE NO CAMPEONATO
							campeonato.getSquads().put(nomeEscuderia, equipe);
							
							pilostosTemp = new ArrayList<LogicSimulaF1_RebornPilot>();
							engenheirosTemp = new ArrayList<LogicSimulaF1_RebornEngineer>();
							carrosTemp = new ArrayList<LogicSimulaF1_RebornCar>();


						}

						if(qName.equals("piloto")){//Nome, engenheiro, equipe
							pilotoTemp = new LogicSimulaF1_RebornPilot();
							pilotoTemp.setName(nomePiloto);
							pilotoTemp.setEngineer(engTemp);
							pilotoTemp.setPoints(pontosPiloto);	
							pilotoTemp.setSigla(abreviacao);
							pilotoTemp.setCar(carroTemp);
							pilotoTemp.setNacionality(nac);
							
							carroTemp.setPilot(pilotoTemp);
							engTemp.setPilotName(pilotoTemp.getName());		

							pilostosTemp.add(pilotoTemp);
							engenheirosTemp.add(engTemp);
							carrosTemp.add(carroTemp);
							

						}

						if(qName.equals("engenheiro")){//String nomeEng, Equipe equipe
							engTemp = new LogicSimulaF1_RebornEngineer();
							engTemp.setName(nomeDoEngenheiro);	

						}

						if(qName.equals("carro")){ //construtor do carro: new Pneu(tipoPneu, 100.0f), piloto, equipe, pesoCarro
							carroTemp = new LogicSimulaF1_RebornCar();
							carroTemp.setTier(new LogicSimulaF1_RebornTire(tipoPneu, 100.0f));
							carroTemp.setWeight(pesoCarro);	
							carroTemp.setCarNumber(numCarro);
							carroTemp.setCarColor(cor);
							carroTemp.setFuel(litrosCombustivel);

						}

						if(qName.equals("mecanico")){//construtor de mecanico: String nomeMec, Equipe equipe
							mecanicoTemp = new LogicSimulaF1_RebornMechanic();
							mecanicoTemp.setName(nomeMecanico);
						}


					}

					public void characters(char ch[], int start, int length) throws SAXException {

						//PARA EQUIPE
						if(equipe) equipe = false;	

						if(escuderia){
							escuderia = false;
							nomeEscuderia = new String(ch, start,length);
						}

						if(pontuacaoEquipe){
							pontuacaoEquipe = false;
							pontosEquipe = Integer.parseInt(new String(ch, start,length));
						}

						if(mecanico){
							mecanico = false;
							nomeMecanico = new String(ch, start,length);
						}

						//PARA PILOTO

						if(piloto) piloto = false;

						if(nome) {
							nome = false;
							nomePiloto = new String(ch, start,length);						
							nomePiloto = nomePiloto.replaceAll("\\t", "");
							nomePiloto = nomePiloto.replaceAll("\\n", "");
						}

						if(abrev) {
							abrev = false;
							abreviacao = new String(ch, start,length);
						}


						if(nacionalidade){
							nacionalidade = false;
							nac = new String(ch, start,length);
						}

						if(pontuacao){
							pontuacao = false;
							pontosPiloto = Integer.parseInt(new String(ch, start,length));
						}

						//PARA ENGENHEIRO

						if(engenheiro) engenheiro = false;

						if(nomeEng){
							nomeEng = false;
							nomeDoEngenheiro = new String(ch, start,length);
						}

						//PARA CARRO

						if(carro) carro = false;
						if(pneu) pneu = false;
						if(combustivel) combustivel = false;

						if(peso){
							peso = false;
							pesoCarro = Integer.parseInt(new String(ch, start,length));
						}


					}

				};

				saxParser.parse(nomeArquivoCarregamento, handler);

			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}


		public static void leArquivoPista(String nomeArquivoCarregamento, final LogicSimulaF1_RebornChampionship campeonato) {
			try {

				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser saxParser = factory.newSAXParser();

				DefaultHandler handler = new DefaultHandler() {

					boolean pista;
					boolean distancia;
					boolean secao;
					boolean velocidade;
					boolean m;
					boolean mInicio;
					boolean pitstop;
					boolean largada;
					boolean inicioLargada;

					boolean mFim;

					String pais;
					String nomePista;

					int pistaID;
					int loc;
					int distanciaPista;
					double veloc;
					int valorM;
					int valorMInicio;
					int valorMFim;
					int voltas;
					int inicioPitstop;
					int fimPiststop;
					int valorInicioLargada;
					int locLargada;

					ArrayList<LogicSimulaF1_RebornCircuit> pistaTemp = new ArrayList<>();
					ArrayList<LogicSimulaF1_RebornSection> secoesTemp = new ArrayList<>();
					PitStop tempPit = new PitStop();

					public void startElement(String uri, String localName, String qName, Attributes attributes) throws org.xml.sax.SAXException {

						if(qName.equals("pista")) {
							pista = true;
							nomePista = attributes.getValue("nome");
							pais = attributes.getValue("pais");
							pistaID = Integer.parseInt(attributes.getValue("seq"));						
							voltas = Integer.parseInt(attributes.getValue("voltas"));
						}

						if(qName.equals("distancia")) distancia = true;	
						
						if(qName.equals("pitstop")){
							pitstop = true;
							inicioPitstop = Integer.parseInt(attributes.getValue("inicio"));
							fimPiststop = Integer.parseInt(attributes.getValue("fim"));						
						}
						
						if(qName.equals("largada")){
							largada = true;
							locLargada = Integer.parseInt(attributes.getValue("loc"));
						}
						
						if(qName.equals("inicioLargada")){
							inicioLargada = true;
						}

						if(qName.equals("secao")) {
							secao = true;
							loc = Integer.parseInt(attributes.getValue("loc"));
						}

						if(qName.equals("velocidade")) velocidade = true;
						if(qName.equals("m")) m = true;
						if(qName.equals("mInicio")) mInicio = true;
						if(qName.equals("mFim")) mFim = true;
					}

					public void endElement(String uri, String localName, String qName) throws SAXException {

						if(qName.equals("pista")) {						
							LogicSimulaF1_RebornCircuit pista = new LogicSimulaF1_RebornCircuit();
							pista.setName(nomePista);
							pista.setCountry(pais);
							pista.setTempo(0);
							pista.setId(pistaID);
							pista.setLenghtCircuit(distanciaPista);
							pista.setLaps(voltas);
							pista.setPitStops(new HashMap<LogicSimulaF1_RebornTeam,PitStop>());
							//pista.setEquipes(campeonato.getSquads());	
							pista.setStart(valorInicioLargada);
							pista.setChampionship(campeonato);

							ArrayList<LogicSimulaF1_RebornSection> hashSecao = new ArrayList<>();						
							for(LogicSimulaF1_RebornSection sec: secoesTemp){
								sec.setCircuit(pista);
								hashSecao.add(sec);
							}

							pista.setSctions(hashSecao);
							pistaTemp.add(pista);
							campeonato.setCircuits(pistaTemp);

						}

						if (qName.equals("secao")) {
							LogicSimulaF1_RebornSection a = new LogicSimulaF1_RebornSection(veloc, loc, valorM, valorMInicio, valorMFim);
							secoesTemp.add(a);
						}
						
						if (qName.equals("pitstop")) {
							tempPit.setStart(inicioPitstop);
							tempPit.setFim(fimPiststop);						
						}
						
						

					}

					public void characters(char ch[], int start, int length) throws SAXException {

						if(pista) pista = false;

						if(distancia){
							distancia = false;
							distanciaPista = Integer.parseInt(new String(ch, start,length));
						}
						
						if(pitstop) pitstop = false;
						
						if(largada) largada = false;						
							
						if(inicioLargada){
							inicioLargada = false;
							valorInicioLargada = Integer.parseInt(new String(ch, start,length));
						}

						if(secao) secao = false;

						if(velocidade){
							velocidade = false;
							veloc = Double.parseDouble(new String(ch, start,length));
						}

						if(m){
							m = false;
							valorM = Integer.parseInt(new String(ch, start,length));
						}

						if(mInicio){
							mInicio = false;
							valorMInicio = Integer.parseInt(new String(ch, start,length));						
						}

						if(mFim){
							mFim = false;
							valorMFim = Integer.parseInt(new String(ch, start,length));						
						}	

					}

				};

				saxParser.parse(nomeArquivoCarregamento, handler);


			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	}

