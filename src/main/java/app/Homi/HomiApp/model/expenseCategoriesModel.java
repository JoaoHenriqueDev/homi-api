package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "expense_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class expenseCategoriesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "icon")
    private String icon;
    @Column(name = "color")
    private String color;
}
