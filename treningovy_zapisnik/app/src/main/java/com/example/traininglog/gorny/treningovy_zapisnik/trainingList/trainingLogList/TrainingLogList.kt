package com.example.traininglog.gorny.treningovy_zapisnik.trainingList.trainingLogList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.traininglog.gorny.treningovy_zapisnik.databinding.FragmentTraininglogListBinding
import com.example.traininglog.gorny.treningovy_zapisnik.trainingList.LogListApplication
import com.google.android.material.dialog.MaterialAlertDialogBuilder


/**
 * Main fragment displaying details for all items in the database.
 */
class TrainingLogList : Fragment() {

    private val viewModel: LogViewModel by activityViewModels {
        LogViewModelFactory(
            (activity?.application as LogListApplication).database.trainingLogRowDao(),
            requireContext()
        )
    }

    private var _binding: FragmentTraininglogListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Thread.setDefaultUncaughtExceptionHandler { paramThread, paramThrowable ->
            Log.e(
                "Error" + Thread.currentThread().stackTrace[2],
                paramThrowable.localizedMessage
            )

            Toast.makeText(requireContext(),"Error" + Thread.currentThread().stackTrace[2],Toast.LENGTH_LONG).show()
        }

        // Inflate the layout for this fragment
        _binding = FragmentTraininglogListBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Instantiates headerAdapter and trainingLogsRowsAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val trainingLogsRowsAdapter = LogListAdapter {
            val action = TrainingLogListDirections.actionMainFragmentToTrainingLogItem(it.id)
            this.findNavController().navigate(action)
        }
        val concatAdapter = ConcatAdapter(headerAdapter, trainingLogsRowsAdapter)

        binding.trainingLogList.layoutManager = LinearLayoutManager (this.context)
        binding.trainingLogList.adapter = concatAdapter

        // Attach an observer on the allItems list to update the UI automatically when the data
        // changes
        viewModel.allTrainingLogs.observe(this.viewLifecycleOwner) { items ->
            items.let {
                trainingLogsRowsAdapter.submitList(it)
                headerAdapter.updateTrainingLogCount(it.size)

            }
        }


        binding.floatingButtonAdd.setOnClickListener {
            val action = TrainingLogListDirections.actionMainFragmentToAddTrainingLog("Add training log")
            findNavController().navigate(action)
        }

        binding.floatingButtondeleted.setOnClickListener {
            showConfirmationDialog()
        }


    }

    /**
     * Deletes the current item and navigates to the list fragment.
     */
    private fun deleteAllTrainingLogs() {
        viewModel.deleteAllItems()
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Deleting all training logs")
            .setMessage("Are you sure that you want delete ALL training logs?")
            .setCancelable(false)
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Yes") { _, _ ->
                deleteAllTrainingLogs()
            }
            .show()
    }



}

