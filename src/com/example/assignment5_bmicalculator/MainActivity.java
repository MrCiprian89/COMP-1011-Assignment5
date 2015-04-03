package com.example.assignment5_bmicalculator;



import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private double _myHeight;
	private double _myWeight;
	private double BMI;
	
	//is true if metric , false if imperial
	private boolean metricOrImperial = true;
	
	private TextView _heightTextView;
	private TextView _weightTextView;
	private EditText _myHeightEditText;
	private EditText _myWeightEditText;
	private EditText _BMIEditText;
	Button unitsButton;
	Button calculateButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 //create a reference to my Controls
		this._heightTextView = (TextView) findViewById(R.id.heightTextView);
		this._weightTextView = (TextView) findViewById(R.id.weightTextView);
        this._myHeightEditText = (EditText) findViewById(R.id.editHeight);
        this._myWeightEditText = (EditText) findViewById(R.id.editWidth);
        this._BMIEditText = (EditText) findViewById(R.id.BMIeditText);		
        unitsButton = (Button) findViewById(R.id.changeUnitsButton);
        calculateButton = (Button) findViewById(R.id.calculateButton);
        
        //make an event that changes the text of the units button and sets the calculations of BMI units
        OnClickListener oclUnitsButton = new OnClickListener() {
            @Override
            public void onClick(View v) {
            	if (metricOrImperial){
            	unitsButton.setText("Imperial Units");
            	metricOrImperial = false;
            	_heightTextView.setText("My Height (inches):");
            	_weightTextView.setText("My Weight (pounds):");
            }
            	else{
            		unitsButton.setText("Metric Units");
                	metricOrImperial = true;
                	_heightTextView.setText("My Height (metres):");
                	_weightTextView.setText("My Weight (kilograms):");
            	}
            }
          };
          //An event that calculates the BMI using height and weight based on the type of units selected and displays the result in the BMI EditTExt view.
          OnClickListener oclcalculateButton = new OnClickListener() {
              @Override
              public void onClick(View v) {
              	if (metricOrImperial){
              		BMI = _myWeight / (_myHeight * _myHeight);
              		_BMIEditText.setText(String.format("%.02f", BMI));
              }
              	else{
              		BMI = (_myWeight * 703)/ (_myHeight * _myHeight);
              		//displayBMI = String.valueOf(BMI);
              		_BMIEditText.setText(String.format("%.02f", BMI));
              	}
              }
            };
        
          // assign click listener to the OK button (btnOK)
          unitsButton.setOnClickListener(oclUnitsButton);
          calculateButton.setOnClickListener(oclcalculateButton);
          this._myHeightEditText.addTextChangedListener(EditTextWatcher);
          this._myWeightEditText.addTextChangedListener(EditTextWatcher);
	}//END onCreate
	
	
	   private TextWatcher EditTextWatcher = new TextWatcher(){
	    	
	    	@Override
	    	public void onTextChanged(CharSequence s , int start, int before, int count){
	    		try{
	    		       if (_myHeightEditText.getText().hashCode() == s.hashCode())
	    		        {
	    		    	   _myHeight = Double.parseDouble(s.toString());
	    		        }
	    		        else if (_myWeightEditText.getText().hashCode() == s.hashCode())
	  		        {
	    		        	_myWeight = Double.parseDouble(s.toString());
	    		        }
	    			
	    		}
	    		catch(NumberFormatException e){
	    			_myHeight = 0;
	    			_myWeight = 0;
	    		}
	    }//END onTExtChanged
	    	
	        @Override
	        public void afterTextChanged(Editable s){}
	        
	        @Override
	        public void beforeTextChanged(CharSequence s , int start, int count, int after){}
	   };//END TextWatcher
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	};
}
