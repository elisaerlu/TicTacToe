package com.example.TicTacToe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    TextView m_tv;
    Button m_button;
    Spinner m_spinner;

    TicTacToe m_ttt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        m_tv = (TextView) findViewById( R.id.textview );
        m_button = (Button) findViewById( R.id.button );
        m_spinner = (Spinner) findViewById( R.id.spinner );

        m_ttt = new TicTacToe();

        updateDisplay();
    }


    public void updateDisplay()
    {
        ArrayList<String> actionsStr = new ArrayList<String>();
        List<TicTacToe.Move> actions = m_ttt.getActions();
        for ( TicTacToe.Move action : actions ) {
            actionsStr.add( action.toString() );
        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>( this, android.R.layout.simple_spinner_dropdown_item, actionsStr );

        m_spinner.setAdapter( arrayAdapter );
        m_tv.setText( m_ttt.toString() );
    }


    public void buttonPlay( View view )
    {
        Button button = (Button) view;

        Log.d("***** TicTacToe", "Button clicked");
        Object action = m_spinner.getSelectedItem();
        if ( action != null ) {
            //Toast.makeText( getApplicationContext(), action.toString(), Toast.LENGTH_LONG ).show();
            TicTacToe.Move move = m_ttt.strToMove( action.toString() );
            if ( move != null ) {
                m_ttt.makeMove( move );
                updateDisplay();
                if ( m_ttt.isGameOver() ) {
                   Toast.makeText( getApplicationContext(), "Game over!", Toast.LENGTH_LONG ).show();
               }
            }

        }
    }

    public void buttonReset( View view )
    {
        m_ttt.reset();
        updateDisplay();
    }

}
