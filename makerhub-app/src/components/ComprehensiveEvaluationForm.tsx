import { useState, useEffect, useRef } from 'react';
import { Form, Button, Card, Row, Col, Accordion } from 'react-bootstrap';
import { DailyEvaluation } from '../types';
import '../styles/autoGrowTextarea.css';

interface ComprehensiveEvaluationFormProps {
  studentId: string;
  onSave: (evaluation: Omit<DailyEvaluation, 'id'>) => void;
  editingEvaluation?: DailyEvaluation;
  onCancelEdit?: () => void;
}

const ComprehensiveEvaluationForm = ({ studentId, onSave, editingEvaluation, onCancelEdit }: ComprehensiveEvaluationFormProps) => {
  const textareaRefs = useRef<{ [key: string]: HTMLTextAreaElement | null }>({});

  const autoGrow = (element: HTMLTextAreaElement) => {
    element.style.height = 'auto';
    element.style.height = element.scrollHeight + 'px';
  };

  const [evaluation, setEvaluation] = useState({
    date: new Date().toISOString().split('T')[0],
    professional: '',
    activityName: '',
    activityObjective: '',
    activitySteps: [] as string[],
    complexityLevel: 'medio' as const,
    emotionalRegulation: 'regulado' as const,
    emotionalStrategies: '',
    socialInteraction: 'sozinho' as const,
    socialConflicts: '',
    communicationRequest: 'verbal' as const,
    communicationExplanation: 'descreveu' as const,
    vocabulary: '',
    attentionDuration: '10_20min' as const,
    instructionComprehension: 'simples' as const,
    stepSequencing: 'completo_sozinho' as const,
    fineMotor: 'adequada' as const,
    grossMotor: 'adequada' as const,
    motorObservations: '',
    problemSolving: [] as string[],
    problemDescription: '',
    solutionDescription: '',
    reading: 'instrucoes' as const,
    writing: 'registro_escrito' as const,
    orality: 'explicou' as const,
    autonomy: 'moderada' as const,
    performance: 'concluiu_sucesso' as const,
    performanceReason: '',
    strengths: '',
    attentionPoints: '',
    relevantBehaviors: '',
    suggestions: '',
    notes: ''
  });

  useEffect(() => {
    Object.values(textareaRefs.current).forEach(textarea => {
      if (textarea) autoGrow(textarea);
    });
  }, [evaluation]);

  useEffect(() => {
    if (editingEvaluation) {
      setEvaluation({
        date: editingEvaluation.date,
        professional: editingEvaluation.professional,
        activityName: editingEvaluation.activityName,
        activityObjective: editingEvaluation.activityObjective,
        activitySteps: editingEvaluation.activitySteps,
        complexityLevel: editingEvaluation.complexityLevel as any,
        emotionalRegulation: editingEvaluation.emotionalRegulation as any,
        emotionalStrategies: editingEvaluation.emotionalStrategies,
        socialInteraction: editingEvaluation.socialInteraction as any,
        socialConflicts: editingEvaluation.socialConflicts,
        communicationRequest: editingEvaluation.communicationRequest as any,
        communicationExplanation: editingEvaluation.communicationExplanation as any,
        vocabulary: editingEvaluation.vocabulary,
        attentionDuration: editingEvaluation.attentionDuration as any,
        instructionComprehension: editingEvaluation.instructionComprehension as any,
        stepSequencing: editingEvaluation.stepSequencing as any,
        fineMotor: editingEvaluation.fineMotor as any,
        grossMotor: editingEvaluation.grossMotor as any,
        motorObservations: editingEvaluation.motorObservations,
        problemSolving: editingEvaluation.problemSolving,
        problemDescription: editingEvaluation.problemDescription,
        solutionDescription: editingEvaluation.solutionDescription,
        reading: editingEvaluation.reading as any,
        writing: editingEvaluation.writing as any,
        orality: editingEvaluation.orality as any,
        autonomy: editingEvaluation.autonomy as any,
        performance: editingEvaluation.performance as any,
        performanceReason: editingEvaluation.performanceReason,
        strengths: editingEvaluation.strengths,
        attentionPoints: editingEvaluation.attentionPoints,
        relevantBehaviors: editingEvaluation.relevantBehaviors,
        suggestions: editingEvaluation.suggestions,
        notes: editingEvaluation.notes
      });
    }
  }, [editingEvaluation]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!evaluation.professional || !evaluation.activityName) {
      alert('Preencha os campos obrigatórios: Profissional Responsável e Nome da Atividade');
      return;
    }
    
    console.log('Salvando avaliação:', { studentId, ...evaluation });
    onSave({
      studentId,
      ...evaluation
    });
    // Reset form
    setEvaluation({
      date: new Date().toISOString().split('T')[0],
      professional: '',
      activityName: '',
      activityObjective: '',
      activitySteps: [],
      complexityLevel: 'medio',
      emotionalRegulation: 'regulado',
      emotionalStrategies: '',
      socialInteraction: 'sozinho',
      socialConflicts: '',
      communicationRequest: 'verbal',
      communicationExplanation: 'descreveu',
      vocabulary: '',
      attentionDuration: '10_20min',
      instructionComprehension: 'simples',
      stepSequencing: 'completo_sozinho',
      fineMotor: 'adequada',
      grossMotor: 'adequada',
      motorObservations: '',
      problemSolving: [],
      problemDescription: '',
      solutionDescription: '',
      reading: 'instrucoes',
      writing: 'registro_escrito',
      orality: 'explicou',
      autonomy: 'moderada',
      performance: 'concluiu_sucesso',
      performanceReason: '',
      strengths: '',
      attentionPoints: '',
      relevantBehaviors: '',
      suggestions: '',
      notes: ''
    });
  };

  const handleStepChange = (step: string, checked: boolean) => {
    setEvaluation(prev => ({
      ...prev,
      activitySteps: checked 
        ? [...prev.activitySteps, step]
        : prev.activitySteps.filter(s => s !== step)
    }));
  };



  return (
    <Card className="border-0 shadow-sm">
      <Card.Header className="bg-light py-2">
        <div className="d-flex justify-content-between align-items-center">
          <h6 className="mb-0">
            {editingEvaluation ? 'Editando Avaliação' : 'Nova Avaliação'} - Sala Maker
          </h6>
          <Form.Control
            type="date"
            size="sm"
            value={evaluation.date}
            onChange={(e) => setEvaluation(prev => ({ ...prev, date: e.target.value }))}
            style={{ width: 'auto' }}
          />
        </div>
      </Card.Header>
      <Card.Body className="p-2">
        <Form onSubmit={handleSubmit}>
          <Accordion defaultActiveKey="0">
            
            {/* Identificação */}
            <Accordion.Item eventKey="0">
              <Accordion.Header>1. Identificação</Accordion.Header>
              <Accordion.Body>
                <Form.Group className="mb-2">
                  <Form.Label className="small fw-bold">Profissional Responsável</Form.Label>
                  <Form.Select
                    size="sm"
                    value={evaluation.professional}
                    onChange={(e) => setEvaluation(prev => ({ ...prev, professional: e.target.value }))}
                  >
                    <option value="">Selecione o profissional...</option>
                    <option value="Ana Carolina Silva">Ana Carolina Silva</option>
                    <option value="Bruno Santos Oliveira">Bruno Santos Oliveira</option>
                    <option value="Daniel Rodrigues Lima">Daniel Rodrigues Lima</option>
                    <option value="Eduarda Ferreira Souza">Eduarda Ferreira Souza</option>
                    <option value="Fernanda Alves Pereira">Fernanda Alves Pereira</option>
                    <option value="Gabriel Martins Rocha">Gabriel Martins Rocha</option>
                    <option value="Helena Castro Nunes">Helena Castro Nunes</option>
                  </Form.Select>
                </Form.Group>
              </Accordion.Body>
            </Accordion.Item>

            {/* Atividade */}
            <Accordion.Item eventKey="1">
              <Accordion.Header>2. Atividade Realizada</Accordion.Header>
              <Accordion.Body>
                <Row className="g-2">
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">Nome da Atividade/Projeto</Form.Label>
                      <Form.Control
                        size="sm"
                        value={evaluation.activityName}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, activityName: e.target.value }))}
                      />
                    </Form.Group>
                  </Col>
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">Objetivo da Atividade</Form.Label>
                      <Form.Control
                        as="textarea"
                        size="sm"
                        className="auto-grow-textarea"
                        value={evaluation.activityObjective}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, activityObjective: e.target.value }))}
                        ref={(el) => { textareaRefs.current['activityObjective'] = el; }}
                      />
                    </Form.Group>
                  </Col>
                  <Col xs={12}>
                    <Form.Label className="small fw-bold">Etapas Envolvidas</Form.Label>
                    <div className="d-flex flex-wrap gap-2">
                      {['Planejamento', 'Montagem', 'Testes', 'Programação', 'Correções', 'Apresentação'].map(step => (
                        <Form.Check
                          key={step}
                          type="checkbox"
                          label={step}
                          className="small"
                          checked={evaluation.activitySteps.includes(step)}
                          onChange={(e) => handleStepChange(step, e.target.checked)}
                        />
                      ))}
                    </div>
                  </Col>
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">Nível de Complexidade</Form.Label>
                      <div className="d-flex gap-3">
                        {[
                          { value: 'baixo', label: 'Baixo' },
                          { value: 'medio', label: 'Médio' },
                          { value: 'alto', label: 'Alto' }
                        ].map(option => (
                          <Form.Check
                            key={option.value}
                            type="radio"
                            name="complexity"
                            label={option.label}
                            className="small"
                            checked={evaluation.complexityLevel === option.value}
                            onChange={() => setEvaluation(prev => ({ ...prev, complexityLevel: option.value as any }))}
                          />
                        ))}
                      </div>
                    </Form.Group>
                  </Col>
                </Row>
              </Accordion.Body>
            </Accordion.Item>

            {/* Comportamento */}
            <Accordion.Item eventKey="2">
              <Accordion.Header>3. Observação do Comportamento</Accordion.Header>
              <Accordion.Body>
                <Row className="g-2">
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">Regulação Emocional</Form.Label>
                      <Form.Select
                        size="sm"
                        value={evaluation.emotionalRegulation}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, emotionalRegulation: e.target.value as any }))}
                      >
                        <option value="regulado">Regulado</option>
                        <option value="pequena_frustracao">Pequena frustração, recuperou com apoio</option>
                        <option value="frustracao_moderada">Frustração moderada</option>
                        <option value="abandono">Abandono da tarefa / crise</option>
                      </Form.Select>
                    </Form.Group>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">Estratégias que Ajudaram</Form.Label>
                      <div className="d-flex flex-wrap gap-1 mb-2 p-2 border rounded bg-light">
                        {['Pausas', 'Respiração', 'Apoio verbal', 'Modelagem', 'Reforço positivo', 'Redução de estímulos', 'Tempo extra', 'Apoio visual', 'Rotina clara', 'Antecipação', 'Escolha', 'Movimento'].map(strategy => (
                          <span
                            key={strategy}
                            onClick={() => {
                              const current = evaluation.emotionalStrategies;
                              const newValue = current ? `${current}, ${strategy}` : strategy;
                              setEvaluation(prev => ({ ...prev, emotionalStrategies: newValue }));
                            }}
                            className="badge bg-primary"
                            style={{ cursor: 'pointer', fontSize: '0.85rem' }}
                          >
                            {strategy}
                          </span>
                        ))}
                      </div>
                      <Form.Control
                        size="sm"
                        value={evaluation.emotionalStrategies}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, emotionalStrategies: e.target.value }))}
                        placeholder="Clique nas estratégias acima ou digite outras..."
                      />
                    </Form.Group>
                  </Col>
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">Interação Social</Form.Label>
                      <Form.Select
                        size="sm"
                        value={evaluation.socialInteraction}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, socialInteraction: e.target.value as any }))}
                      >
                        <option value="sozinho">Trabalhou sozinho</option>
                        <option value="dupla">Aceitou trabalhar em dupla</option>
                        <option value="mediacao_constante">Necessitou mediação constante</option>
                        <option value="compartilhou">Compartilhou materiais espontaneamente</option>
                        <option value="conflitos">Teve conflitos</option>
                      </Form.Select>
                    </Form.Group>
                    {(evaluation.socialInteraction as string) === 'conflitos' && (
                      <Form.Group className="mb-2">
                        <Form.Label className="small fw-bold">Descrever Conflitos</Form.Label>
                        <Form.Control
                          size="sm"
                          value={evaluation.socialConflicts}
                          onChange={(e) => setEvaluation(prev => ({ ...prev, socialConflicts: e.target.value }))}
                        />
                      </Form.Group>
                    )}
                  </Col>
                </Row>
              </Accordion.Body>
            </Accordion.Item>

            {/* Síntese */}
            <Accordion.Item eventKey="3">
              <Accordion.Header>4. Síntese Final</Accordion.Header>
              <Accordion.Body>
                <Row className="g-2">
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">Pontos Fortes Observados</Form.Label>
                      <div className="d-flex flex-wrap gap-1 mb-2 p-2 border rounded bg-light">
                        {['Criatividade', 'Persistência', 'Foco', 'Colaboração', 'Autonomia', 'Organização', 'Comunicação clara', 'Resolução de problemas', 'Curiosidade', 'Paciência'].map(strength => (
                          <span
                            key={strength}
                            onClick={() => {
                              const current = evaluation.strengths;
                              const newValue = current ? `${current}, ${strength}` : strength;
                              setEvaluation(prev => ({ ...prev, strengths: newValue }));
                            }}
                            className="badge bg-success"
                            style={{ cursor: 'pointer', fontSize: '0.85rem' }}
                          >
                            {strength}
                          </span>
                        ))}
                      </div>
                      <Form.Control
                        as="textarea"
                        size="sm"
                        className="auto-grow-textarea"
                        value={evaluation.strengths}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, strengths: e.target.value }))}
                        ref={(el) => { textareaRefs.current['strengths'] = el; }}
                        placeholder="Clique nos pontos fortes acima ou descreva outros..."
                      />
                    </Form.Group>
                  </Col>
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">Pontos de Atenção</Form.Label>
                      <div className="d-flex flex-wrap gap-1 mb-2 p-2 border rounded bg-light">
                        {['Atenção dispersa', 'Frustração', 'Dificuldade motora', 'Comunicação limitada', 'Dependência', 'Impulsividade', 'Recusa', 'Ansiedade', 'Desorganização'].map(attention => (
                          <span
                            key={attention}
                            onClick={() => {
                              const current = evaluation.attentionPoints;
                              const newValue = current ? `${current}, ${attention}` : attention;
                              setEvaluation(prev => ({ ...prev, attentionPoints: newValue }));
                            }}
                            className="badge bg-warning text-dark"
                            style={{ cursor: 'pointer', fontSize: '0.85rem' }}
                          >
                            {attention}
                          </span>
                        ))}
                      </div>
                      <Form.Control
                        as="textarea"
                        size="sm"
                        className="auto-grow-textarea"
                        value={evaluation.attentionPoints}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, attentionPoints: e.target.value }))}
                        ref={(el) => { textareaRefs.current['attentionPoints'] = el; }}
                        placeholder="Clique nos pontos de atenção acima ou descreva outros..."
                      />
                    </Form.Group>
                  </Col>
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">Sugestões de Metas</Form.Label>
                      <div className="d-flex flex-wrap gap-1 mb-2 p-2 border rounded bg-light">
                        {['Aumentar autonomia', 'Melhorar comunicação', 'Desenvolver foco', 'Trabalhar frustração', 'Fortalecer motricidade', 'Estimular interação social', 'Ampliar vocabulário', 'Praticar sequenciamento', 'Reduzir impulsividade'].map(goal => (
                          <span
                            key={goal}
                            onClick={() => {
                              const current = evaluation.suggestions;
                              const newValue = current ? `${current}, ${goal}` : goal;
                              setEvaluation(prev => ({ ...prev, suggestions: newValue }));
                            }}
                            className="badge bg-info text-dark"
                            style={{ cursor: 'pointer', fontSize: '0.85rem' }}
                          >
                            {goal}
                          </span>
                        ))}
                      </div>
                      <Form.Control
                        as="textarea"
                        size="sm"
                        className="auto-grow-textarea"
                        value={evaluation.suggestions}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, suggestions: e.target.value }))}
                        ref={(el) => { textareaRefs.current['suggestions'] = el; }}
                        placeholder="Clique nas metas acima ou descreva outras..."
                      />
                    </Form.Group>
                  </Col>
                  <Col xs={12}>
                    <Form.Group className="mb-3">
                      <Form.Label className="small fw-bold">Observações Gerais</Form.Label>
                      <Form.Control
                        as="textarea"
                        size="sm"
                        className="auto-grow-textarea"
                        value={evaluation.notes}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, notes: e.target.value }))}
                        ref={(el) => { textareaRefs.current['notes'] = el; }}
                        placeholder="Descreva observações adicionais relevantes..."
                      />
                    </Form.Group>
                  </Col>
                </Row>
              </Accordion.Body>
            </Accordion.Item>

          </Accordion>

          <div className="d-flex gap-2 mt-3">
            {editingEvaluation && onCancelEdit && (
              <Button type="button" variant="secondary" onClick={onCancelEdit} className="flex-fill">
                Cancelar
              </Button>
            )}
            <Button type="submit" variant="primary" className="flex-fill">
              {editingEvaluation ? '? Atualizar Avaliação' : '? Salvar Avaliação'}
            </Button>
          </div>
        </Form>
      </Card.Body>
    </Card>
  );
};

export default ComprehensiveEvaluationForm;