package IMDB.domain;

/*
Para o contexto deste exercício, pensei em criar uma classe (contendo os atributos disponibilizados
pela api) com as seguintes características:
    - Não teria setters, já que vamos apenas receber os dados da API e a classe serviria apenas como uma
      representação deles.
    - Portanto, teria um construtor padrão com os atributos obrigatórios.
    - Todos os atributos serão finals, graças à ausência dos setters.
    - Ela não será interfaceada, nem trabalhará com herança. já que não estamos lidando com outros tipos de
      mídia (séries, documentários, etc)
    - Faz sentido sobrescrever alguns métodos comuns, como getters, toString(), etc

 Depois de montar a classe, percebi que os conceitos e características aplicadas aqui se enquadram perfeitamente
 nos casos de uso de Records. Portanto, decidi transformar a classe em Record para mantê-la mais organizada.
 */
public record Movie(
    String id,
    String rank,
    String title,
    String fullTitle,
    String year,
    String image,
    String crew,
    String imDbRating,
    String imDbRatingCount
){}
//Obs: Poderíamos sobrecarregar o construtor, para caso não seja necessário montar receber todos os atributos