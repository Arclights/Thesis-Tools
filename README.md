# Thesis-Tools
These are the tools created for the master thesis *Task scheduling for dual-arm industrial robots through constraint programming - MiniZinc modeling and solver comparison*.
The tools are free to use under the Apache 2.0 license.
The report can be found [here][https://github.com/Arclights/Master-Thesis-Report/raw/master/report.pdf]

## Data
This directory contains the data for the case study and the MiniZinc model

## Assembly
This is used by AssemblyConv to represent the assembly

## AssemblyConv
`AssemblyConv` is the program used to convert the XML file and the time matrix file into data for the solver. It takes the matrix file and the XML as arguments and produces a MiniZinc file. For a detailed description of the syntax run the program without parameters.

## SchedPrinter
`SchedPrinter` is used for visualising the outputted assembly from the solvers. It creates a Gant diagram in ASCII and outputs it to the screen. To get it to a file one can simply pipe it to one and it can then be shown in a regular text editor. But make sure that text wrapping is turned of as it will interfere with the visualisation. It takes a text file containing the output from the solver. This can easily be obtained by piping the output from a solver into a file. The program also takes an argument whether the text file provided is in the format that the JaCoP solver provides or the format G12 provides. The solvers using the G12 format is G12/FD, Gecode and or-tools, and the solvers using the JaCoP format is JaCoP and Choco3. The output format for Opturion CPX is nknown since we have not been able to run the model on it. When printing from the JaCoP format, the user has to supply the `.dzn` file so the printer can know the name of the tasks. For a detailed description of the syntax run the program without parameters or with `-h` as parameter. The output of the tasks with duration 0 will look weird, but that is because we cannot fit any characters in a box with a width of 0.

## FZNstat
To obtain the statistics used in the evaluation chapter of the report we use`FZNstat`. The program does not take any parameters but will go over all the `.fzn` files in the directory it is run in and produce a result file for each of the `.fzn` files. The names of the result files are the same as the `.fzn` files but with `\_stat` at the end. So for example `jacop.fzn` will get the result file `jacop\_stat`. The result files will contain a little bit more information about individual constraints than what was presented in the report.
