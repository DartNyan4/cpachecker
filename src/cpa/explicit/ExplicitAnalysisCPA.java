package cpa.explicit;

import java.util.ArrayList;
import java.util.List;

import cfa.objectmodel.CFAFunctionDefinitionNode;
import cfa.objectmodel.c.FunctionDefinitionNode;
import cpa.common.interfaces.AbstractDomain;
import cpa.common.interfaces.AbstractElement;
import cpa.common.interfaces.ConfigurableProgramAnalysis;
import cpa.common.interfaces.MergeOperator;
import cpa.common.interfaces.StopOperator;
import cpa.common.interfaces.TransferRelation;
import exceptions.CPAException;

public class ExplicitAnalysisCPA implements ConfigurableProgramAnalysis {

	private AbstractDomain abstractDomain;
	private MergeOperator mergeOperator;
	private StopOperator stopOperator;
	private TransferRelation transferRelation;

	public ExplicitAnalysisCPA (String mergeType, String stopType) throws CPAException {
		ExplicitAnalysisDomain explicitAnalysisDomain = new ExplicitAnalysisDomain ();
		MergeOperator explicitAnalysisMergeOp = null;
		if(mergeType.equals("sep")){
			explicitAnalysisMergeOp = new ExplicitAnalysisMergeSep (explicitAnalysisDomain);
		}
		if(mergeType.equals("join")){
			explicitAnalysisMergeOp = new ExplicitAnalysisMergeJoin (explicitAnalysisDomain);
		}

		StopOperator explicitAnalysisStopOp = null;

		if(stopType.equals("sep")){
			explicitAnalysisStopOp = new ExplicitAnalysisStopSep (explicitAnalysisDomain);
		}
		if(stopType.equals("join")){
			explicitAnalysisStopOp = new ExplicitAnalysisStopJoin (explicitAnalysisDomain);
		}

		TransferRelation explicitAnalysisTransferRelation = new ExplicitAnalysisTransferRelation (explicitAnalysisDomain);

		this.abstractDomain = explicitAnalysisDomain;
		this.mergeOperator = explicitAnalysisMergeOp;
		this.stopOperator = explicitAnalysisStopOp;
		this.transferRelation = explicitAnalysisTransferRelation;
	}

	public AbstractDomain getAbstractDomain ()
	{
		return abstractDomain;
	}

	public MergeOperator getMergeOperator ()
	{
		return mergeOperator;
	}

	public StopOperator getStopOperator ()
	{
		return stopOperator;
	}

	public TransferRelation getTransferRelation ()
	{
		return transferRelation;
	}

    public AbstractElement getInitialElement (CFAFunctionDefinitionNode node)
    {
        return new ExplicitAnalysisElement();
    }


}
