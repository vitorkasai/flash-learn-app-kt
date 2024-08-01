CREATE TABLE "tb_deck"(
    "id_deck"      BIGINT       NOT NULL AUTO_INCREMENT,
    "tx_categoria" VARCHAR(255) NOT NULL,
    PRIMARY KEY ("id_deck")
) ENGINE=InnoDB
  AUTO_INCREMENT=185
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE "tb_card"(
    "id_card" BIGINT NOT NULL AUTO_INCREMENT,
    "tx_front" VARCHAR(255) NOT NULL,
    "tx_back" VARCHAR(255) NOT NULL,
    "id_deck" BIGINT NOT NULL,
    PRIMARY KEY ("id_card"),
    KEY "id_deck" ("id_deck"),
    CONSTRAINT "tb_card_ibfk_1" FOREIGN KEY ("id_deck") REFERENCES "tb_deck" ("id_deck") ON DELETE CASCADE
) ENGINE=InnoDB
  AUTO_INCREMENT=155
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;
