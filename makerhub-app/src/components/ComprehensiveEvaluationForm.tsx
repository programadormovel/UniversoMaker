import { useState, useEffect, useRef } from 'react';
import { Form, Button, Card, Row, Col, Accordion } from 'react-bootstrap';
import { DailyEvaluation } from '../types';
import { personService } from '../services/personService';
import { evaluationApiService } from '../services/evaluationApiService';
import '../styles/autoGrowTextarea.css';

interface ComprehensiveEvaluationFormProps {
  studentId: string;
  onSave: (evaluation: Omit<DailyEvaluation, 'id'>) => void;
  editingEvaluation?: DailyEvaluation;
  onCancelEdit?: () => void;
}

const ComprehensiveEvaluationForm = ({ studentId, onSave, editingEvaluation, onCancelEdit }: ComprehensiveEvaluationFormProps) => {
  const textareaRefs = useRef<{ [key: string]: HTMLTextAreaElement | null }>({});
  const [professionals, setProfessionals] = useState<Array<{id: string, name: string}>>([]);

  const autoGrow = (element: HTMLTextAreaElement) => {
    element.style.height = 'auto';
    element.style.height = element.scrollHeight + 'px';
  };

  const [evaluation, setEvaluation] = useState({
    date: new Date().toISOString().split('T')[0],
    professional: '',
    professionalId: '',
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
    const loadProfessionals = async () => {
      const data = await personService.fetchProfessionals();
      setProfessionals(data);
    };
    loadProfessionals();
  }, []);

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
        professionalId: '',
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

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!evaluation.professional || !evaluation.activityName) {
      alert('Preencha os campos obrigatÃ³rios: Profissional ResponsÃ¡vel e Nome da Atividade');
      return;
    }
    
    console.log('Salvando avaliaÃ§Ã£o:', { studentId, ...evaluation });
    
    try {
      // 1. Criar avaliaÃ§Ã£o principal
      const evaluationPayload = {
        evaluationName: evaluation.activityName,
        objective: evaluation.activityObjective,
        typeId: 1, // Tipo: Sala Maker
        studentId: parseInt(studentId),
        professionalId: parseInt(evaluation.professionalId),
        notes: `Data: ${evaluation.date}\nEtapas: ${evaluation.activitySteps.join(', ')}\nComplexidade: ${evaluation.complexityLevel}`,
        rate: 0,
        isActive: true
      };
      
      const savedEvaluation = await evaluationApiService.createEvaluation(evaluationPayload);
      console.log('AvaliaÃ§Ã£o criada com ID:', savedEvaluation.id);
      
      // 2. Criar itens de avaliaÃ§Ã£o e respostas
      const items = [
        { name: 'RegulaÃ§Ã£o Emocional', value: evaluation.emotionalRegulation, obs: evaluation.emotionalStrategies },
        { name: 'InteraÃ§Ã£o Social', value: evaluation.socialInteraction, obs: evaluation.socialConflicts },
        { name: 'ComunicaÃ§Ã£o - Pedido', value: evaluation.communicationRequest, obs: '' },
        { name: 'ComunicaÃ§Ã£o - ExplicaÃ§Ã£o', value: evaluation.communicationExplanation, obs: evaluation.vocabulary },
        { name: 'DuraÃ§Ã£o da AtenÃ§Ã£o', value: evaluation.attentionDuration, obs: '' },
        { name: 'CompreensÃ£o de InstruÃ§Ãµes', value: evaluation.instructionComprehension, obs: '' },
        { name: 'Sequenciamento de Etapas', value: evaluation.stepSequencing, obs: '' },
        { name: 'Motricidade Fina', value: evaluation.fineMotor, obs: evaluation.motorObservations },
        { name: 'Motricidade Grossa', value: evaluation.grossMotor, obs: '' },
        { name: 'ResoluÃ§Ã£o de Problemas', value: evaluation.problemSolving.join(', '), obs: evaluation.problemDescription },
        { name: 'Leitura', value: evaluation.reading, obs: '' },
        { name: 'Escrita', value: evaluation.writing, obs: '' },
        { name: 'Oralidade', value: evaluation.orality, obs: '' },
        { name: 'Autonomia', value: evaluation.autonomy, obs: '' },
        { name: 'Desempenho', value: evaluation.performance, obs: evaluation.performanceReason },
        { name: 'Pontos Fortes', value: evaluation.strengths, obs: '' },
        { name: 'Pontos de AtenÃ§Ã£o', value: evaluation.attentionPoints, obs: '' },
        { name: 'SugestÃµes', value: evaluation.suggestions, obs: '' },
        { name: 'ObservaÃ§Ãµes Gerais', value: evaluation.notes, obs: '' }
      ];
      
      for (const item of items) {
        if (item.value) {
          // Criar item
          const itemPayload = {
            itemName: item.name,
            description: item.name,
            typeId: 1,
            isActive: true
          };
          
          const savedItem = await evaluationApiService.createItem(itemPayload);
          
          // Criar resposta
          const answerPayload = {
            evaluationId: savedEvaluation.id,
            evaluationItemId: savedItem.id,
            answer: String(item.value),
            observation: item.obs || '',
            isActive: true
          };
          
          await evaluationApiService.saveItemAnswer(answerPayload);
        }
      }
      
      console.log('AvaliaÃ§Ã£o completa salva com sucesso!');
      alert('AvaliaÃ§Ã£o salva com sucesso!');
      
      // Salvar localmente tambÃ©m
      onSave({
        studentId,
        ...evaluation
      });
      
      // Limpar formulÃ¡rio
      setEvaluation({
      date: new Date().toISOString().split('T')[0],
      professional: '',
      professionalId: '',
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
    } catch (err) {
      console.error('Erro ao salvar avaliaÃ§Ã£o:', err);
      alert('Erro ao salvar avaliaÃ§Ã£o. Verifique os dados e tente novamente.');
    }
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
            {editingEvaluation ? 'Editando AvaliaÃ§Ã£o' : 'Nova AvaliaÃ§Ã£o'} - Sala Maker
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
            
            <Accordion.Item eventKey="0">
              <Accordion.Header>1. IdentificaÃ§Ã£o</Accordion.Header>
              <Accordion.Body>
                <Form.Group className="mb-2">
                  <Form.Label className="small fw-bold">Profissional ResponsÃ¡vel</Form.Label>
                  <Form.Select
                    size="sm"
                    value={evaluation.professionalId}
                    onChange={(e) => {
                      const selectedProf = professionals.find(p => p.id === e.target.value);
                      setEvaluation(prev => ({ 
                        ...prev, 
                        professionalId: e.target.value,
                        professional: selectedProf?.name || ''
                      }));
                    }}
                  >
                    <option value="">Selecione o profissional...</option>
                    {professionals.map(prof => (
                      <option key={prof.id} value={prof.id}>{prof.name}</option>
                    ))}
                  </Form.Select>
                </Form.Group>
              </Accordion.Body>
            </Accordion.Item>

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
                      {['Planejamento', 'Montagem', 'Testes', 'ProgramaÃ§Ã£o', 'CorreÃ§Ãµes', 'ApresentaÃ§Ã£o'].map(step => (
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
                      <Form.Label className="small fw-bold">NÃ­vel de Complexidade</Form.Label>
                      <div className="d-flex gap-3">
                        {[
                          { value: 'baixo', label: 'Baixo' },
                          { value: 'medio', label: 'MÃ©dio' },
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

            <Accordion.Item eventKey="2">
              <Accordion.Header>3. ObservaÃ§Ã£o do Comportamento</Accordion.Header>
              <Accordion.Body>
                <Row className="g-2">
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">RegulaÃ§Ã£o Emocional</Form.Label>
                      <Form.Select
                        size="sm"
                        value={evaluation.emotionalRegulation}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, emotionalRegulation: e.target.value as any }))}
                      >
                        <option value="regulado">Regulado</option>
                        <option value="pequena_frustracao">Pequena frustraÃ§Ã£o, recuperou com apoio</option>
                        <option value="frustracao_moderada">FrustraÃ§Ã£o moderada</option>
                        <option value="abandono">Abandono da tarefa / crise</option>
                      </Form.Select>
                    </Form.Group>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">EstratÃ©gias que Ajudaram</Form.Label>
                      <div className="d-flex flex-wrap gap-1 mb-2 p-2 border rounded bg-light">
                        {['Pausas', 'RespiraÃ§Ã£o', 'Apoio verbal', 'Modelagem', 'ReforÃ§o positivo', 'ReduÃ§Ã£o de estÃ­mulos', 'Tempo extra', 'Apoio visual', 'Rotina clara', 'AntecipaÃ§Ã£o', 'Escolha', 'Movimento'].map(strategy => (
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
                        placeholder="Clique nas estratÃ©gias acima ou digite outras..."
                      />
                    </Form.Group>
                  </Col>
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">InteraÃ§Ã£o Social</Form.Label>
                      <Form.Select
                        size="sm"
                        value={evaluation.socialInteraction}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, socialInteraction: e.target.value as any }))}
                      >
                        <option value="sozinho">Trabalhou sozinho</option>
                        <option value="dupla">Aceitou trabalhar em dupla</option>
                        <option value="mediacao_constante">Necessitou mediaÃ§Ã£o constante</option>
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

            <Accordion.Item eventKey="3">
              <Accordion.Header>4. SÃ­ntese Final</Accordion.Header>
              <Accordion.Body>
                <Row className="g-2">
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">Pontos Fortes Observados</Form.Label>
                      <div className="d-flex flex-wrap gap-1 mb-2 p-2 border rounded bg-light">
                        {['Criatividade', 'PersistÃªncia', 'Foco', 'ColaboraÃ§Ã£o', 'Autonomia', 'OrganizaÃ§Ã£o', 'ComunicaÃ§Ã£o clara', 'ResoluÃ§Ã£o de problemas', 'Curiosidade', 'PaciÃªncia'].map(strength => (
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
                      <Form.Label className="small fw-bold">Pontos de AtenÃ§Ã£o</Form.Label>
                      <div className="d-flex flex-wrap gap-1 mb-2 p-2 border rounded bg-light">
                        {['AtenÃ§Ã£o dispersa', 'FrustraÃ§Ã£o', 'Dificuldade motora', 'ComunicaÃ§Ã£o limitada', 'DependÃªncia', 'Impulsividade', 'Recusa', 'Ansiedade', 'DesorganizaÃ§Ã£o'].map(attention => (
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
                        placeholder="Clique nos pontos de atenÃ§Ã£o acima ou descreva outros..."
                      />
                    </Form.Group>
                  </Col>
                  <Col xs={12}>
                    <Form.Group className="mb-2">
                      <Form.Label className="small fw-bold">SugestÃµes de Metas</Form.Label>
                      <div className="d-flex flex-wrap gap-1 mb-2 p-2 border rounded bg-light">
                        {['Aumentar autonomia', 'Melhorar comunicaÃ§Ã£o', 'Desenvolver foco', 'Trabalhar frustraÃ§Ã£o', 'Fortalecer motricidade', 'Estimular interaÃ§Ã£o social', 'Ampliar vocabulÃ¡rio', 'Praticar sequenciamento', 'Reduzir impulsividade'].map(goal => (
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
                      <Form.Label className="small fw-bold">ObservaÃ§Ãµes Gerais</Form.Label>
                      <Form.Control
                        as="textarea"
                        size="sm"
                        className="auto-grow-textarea"
                        value={evaluation.notes}
                        onChange={(e) => setEvaluation(prev => ({ ...prev, notes: e.target.value }))}
                        ref={(el) => { textareaRefs.current['notes'] = el; }}
                        placeholder="Descreva observaÃ§Ãµes adicionais relevantes..."
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
              {editingEvaluation ? 'âœ“ Atualizar AvaliaÃ§Ã£o' : 'âœ“ Salvar AvaliaÃ§Ã£o'}
            </Button>
          </div>
        </Form>
      </Card.Body>
    </Card>
  );
};

export default ComprehensiveEvaluationForm;
