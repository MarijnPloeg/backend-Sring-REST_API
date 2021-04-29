package nl.marijnploeg.kitereparatie.model.Authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@IdClass(AuthorityKey.class)
public class Authority implements Serializable {

    @Id
    @Column(nullable = false)
    private String email;

    @Id
    @Column(nullable = false)
    private String authority;

}
