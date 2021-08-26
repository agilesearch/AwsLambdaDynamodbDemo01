package se.agilesearch.helloworld.aws.lambda.dynamodb.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonRequest {

    private String firstName;

    private String lastName;
}
