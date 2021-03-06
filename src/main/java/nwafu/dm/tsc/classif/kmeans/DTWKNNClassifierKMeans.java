/*******************************************************************************
 * Copyright (C) 2014 Anonymized
 * Contributors:
 * 	Anonymized
 * 
 * This file is part of ICDM2014SUBMISSION. 
 * This is a program related to the paper "Dynamic Time Warping Averaging of 
 * Time Series allows more Accurate and Faster Classification" submitted to the
 * 2014 Int. Conf. on Data Mining.
 * 
 * ICDM2014SUBMISSION is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * ICDM2014SUBMISSION is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ICDM2014SUBMISSION.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package nwafu.dm.tsc.classif.kmeans;

import nwafu.dm.tsc.items.ClassedSequence;
import nwafu.dm.tsc.items.Sequence;

import java.util.ArrayList;

import nwafu.dm.tsc.classif.Prototyper;
import weka.core.Instances;

public class DTWKNNClassifierKMeans extends Prototyper {

	private static final long serialVersionUID = -4009393603913976158L;
	
	public DTWKNNClassifierKMeans() {
		super();
	}

	@Override
	protected void buildSpecificClassifier(Instances data) {
		
		ArrayList<String> classes = new ArrayList<String>(classedData.keySet());
		
		for (String clas : classes) {
			// if the class is empty, continue
			if(classedData.get(clas).isEmpty()) 
				continue;
			KMeansSymbolicSequence kmeans = new KMeansSymbolicSequence(nbPrototypesPerClass, classedData.get(clas));
			kmeans.cluster();
			ArrayList<Sequence>[] aff=kmeans.affectation;
			for (int i = 0; i < aff.length; i++) {
				System.out.println("aaaafff");
				for (Sequence s : aff[0]) {
					System.out.println(s);
				}
			}
			for (int i = 0; i < kmeans.centers.length; i++) {
				if(kmeans.centers[i]!=null){ //~ if empty cluster
					ClassedSequence s = new ClassedSequence(kmeans.centers[i], clas);
					prototypes.add(s);
				}
			}
		}
	}
}
