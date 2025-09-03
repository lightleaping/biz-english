-- Insert Scenario
INSERT INTO scenario (code, title, description)
VALUES ('PRJ_DELAY_REPORT_V1', '프로젝트 지연 보고 (Business English)',
        'PM에게 일정 지연을 비즈니스 톤으로 보고하고, 리스크와 액션을 제시한다.');

SET @scn_id = LAST_INSERT_ID();

-- Stages
INSERT INTO stage (scenario_id, name, content, order_no, is_terminal, audio_url)
VALUES
    (@scn_id, 'Opening',
     'Hi [Manager Name], I’d like to give you a quick update on the timeline...',
     1, FALSE, NULL),
    (@scn_id, 'RiskSummary',
     'We’re currently one week behind due to integration issues with the payment gateway...',
     2, FALSE, NULL),
    (@scn_id, 'AskSupport',
     'To get back on track, we need your help to prioritize...',
     3, FALSE, NULL),
    (@scn_id, 'Closing',
     'If you agree, we can proceed with the adjusted plan and share a revised timeline by EOD.',
     4, TRUE, NULL);

-- Capture Stage IDs
SELECT id INTO @st_open FROM stage WHERE scenario_id=@scn_id AND name='Opening';
SELECT id INTO @st_risk FROM stage WHERE scenario_id=@scn_id AND name='RiskSummary';
SELECT id INTO @st_ask  FROM stage WHERE scenario_id=@scn_id AND name='AskSupport';
SELECT id INTO @st_close FROM stage WHERE scenario_id=@scn_id AND name='Closing';

-- Choices
INSERT INTO choice (stage_id, text, next_stage_id, score_delta, hint) VALUES
                                                                          (@st_open, 'Lead with context + ownership', @st_risk,  2, '간단한 배경 + 책임 인정'),
                                                                          (@st_open, 'Blame vendor first',            @st_risk, -2, '책임 전가 시작은 비추천'),
                                                                          (@st_risk, 'Propose concrete mitigation (parallelize tests, add hotfix slot)', @st_ask, 3, '구체적 액션 제시'),
                                                                          (@st_risk, 'Vague promise to catch up',                             @st_ask, -1, '모호한 표현은 신뢰 저하'),
                                                                          (@st_ask,  'Ask to de-scope non-critical features',                 @st_close, 2, NULL),
                                                                          (@st_ask,  'Ask for additional reviewers',                          @st_close, 1, NULL);

-- Link first stage
UPDATE scenario SET first_stage_id = @st_open WHERE id = @scn_id;
