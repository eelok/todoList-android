package com.eelok.todoister;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.eelok.todoister.entity.Priority;
import com.eelok.todoister.entity.Task;
import com.eelok.todoister.entity.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    private EditText enterTodo;
    private ImageButton calendarButton;
    private ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private int selectedButtonId;
    private ImageButton saveButton;
    private CalendarView calendarView;
    private Group calendarGroup;

    private TaskViewModel taskViewModel;
    public BottomSheetFragment() {
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        calendarGroup = view.findViewById(R.id.calendar_group);
        calendarView = view.findViewById(R.id.calendar_view);
        calendarButton = view.findViewById(R.id.today_calendar_button);

        priorityButton = view.findViewById(R.id.priority_todo_button);
        priorityRadioGroup = view.findViewById(R.id.radioGroup_priority);

        enterTodo = view.findViewById(R.id.enter_todo_et);
        saveButton = view.findViewById(R.id.save_todo_button);

        Chip todayChip = view.findViewById(R.id.today_chip);
        Chip tomorrowChip = view.findViewById(R.id.tomorrow_chip);
        Chip nextWeekChip = view.findViewById(R.id.next_week_chip);

        //todo ???
        taskViewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getActivity().getApplication())
                .create(TaskViewModel.class);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userTodo = enterTodo.getText().toString().trim();
                if(!TextUtils.isEmpty(userTodo)){
                    Task taskToInters = new Task(
                            userTodo,
                            Priority.HIGH,
                            Calendar.getInstance().getTime(),
                            Calendar.getInstance().getTime(),
                            false
                    );
                    taskViewModel.createNewTask(taskToInters);
                }

            }
        });
    }
}