-- SCENARIO
CREATE TABLE scenario (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          code VARCHAR(64) NOT NULL UNIQUE,
                          title VARCHAR(200) NOT NULL,
                          description TEXT,
                          first_stage_id BIGINT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- STAGE
CREATE TABLE stage (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       scenario_id BIGINT NOT NULL,
                       name VARCHAR(150) NOT NULL,
                       content TEXT NOT NULL,
                       order_no INT NOT NULL,
                       is_terminal BOOLEAN NOT NULL DEFAULT FALSE,
                       audio_url VARCHAR(500) NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       CONSTRAINT fk_stage_scenario FOREIGN KEY (scenario_id) REFERENCES scenario(id)
                           ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- CHOICE
CREATE TABLE choice (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        stage_id BIGINT NOT NULL,
                        text VARCHAR(500) NOT NULL,
                        next_stage_id BIGINT NULL,
                        score_delta INT NOT NULL DEFAULT 0,
                        hint VARCHAR(500) NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        CONSTRAINT fk_choice_stage FOREIGN KEY (stage_id) REFERENCES stage(id)
                            ON DELETE CASCADE,
                        CONSTRAINT fk_choice_next_stage FOREIGN KEY (next_stage_id) REFERENCES stage(id)
                            ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- first_stage_id는 동일 시나리오의 stage만 참조하도록 트리거/체크는 애플리케이션에서 보장
