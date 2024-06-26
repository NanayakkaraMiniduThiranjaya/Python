## I declare that my work contains no examples of misconduct, such as plagiarism, or collusion. 

## Any code taken from other sources is referenced within my code soluƟon.

## Student ID: w2055093
## Date:12/07/2023

from graphics import *

progress_count = 0
trailer_count = 0
exclude_count = 0
retriever_count = 0
inputs = []
all_inputs = []

def User():
    G=True
    while (G):
        user_input = input("Enter '1' for students or '2' for stuff :")
        if user_input=="1":
            
            print("\nStudent Interface\n ")
            pass_mark, defer_mark, fail_mark = Input_marks()
            if pass_mark + defer_mark + fail_mark != 120:
                print("\nTotal incorrect.")
            else:
                progression_outcome(pass_mark, defer_mark, fail_mark)
                print("")
                E = False
                A = False
                continue
        elif user_input=="2" :
            print("\nStuff Interface")
            G = False
        else:
            print("Invalid response. Try again.")
            print("")
    
def progression_outcome(n1,n2,n3):
    num_pass    = int(n1/20)
    num_defer   = int(n2/20)
    num_fail    = int(n3/20)

    N1=0
    N2=0
    N3=0
    N4=0
    N5=0

##outcome check    
    if num_pass == 6:
        print("Progress")
        N1 += 1
        N5 += 1
        progression = "Progress"
    elif num_pass == 5:
        print("Progress(module trailer)")
        N2 += 1
        N5 += 1
        progression = "Progress(module trailer)"
    elif num_fail >= 4:
        print("Exclude")
        N4 += 1
        N5 += 1
        progression = "Exclude"
    else:
        print("Do not progress - module retriever")
        N3 += 1
        N5 += 1
        progression = "Module Retriever"

    inputs = [progression, n1, n2, n3]
    all_inputs.append(inputs)

    return N1,N2,N3,N4,N5


def Print_Text(win, content, size, color, x, y):
    text = Text(Point(x, y), content)
    text.setSize(size)
    text.setTextColor(color)
    text.draw(win)


def Text_Edit(win, content, height, size, color, x, y):
    text = Text(Point(x + 45, (y - height * 20) - 15), content)
    text.setSize(size)
    text.setTextColor(color)
    text.draw(win)


def Print_Bar(win, label, height, color, x, y):
    bar = Rectangle(Point(x, y), Point(x + 90, y - height * 20))
    bar.setFill(color)
    bar.draw(win)
    text = Text(Point(x + 45, y  + 20 ),f"{label}")
    text.draw(win)

##horizontal line
def Bar_line(win,x,y):
    line = Line(Point(x,y), Point(x+500,y))
    line.setWidth(3)
    line.draw(win)

##bar graph 
def Bar_graph(progress, trailer, retriever, exclude,total,add_text1,add_text2,a,b):
    win = GraphWin("Progression Outcome Graph", 550, 600)
    win.setBackground("white")
##print bar def calling
    Print_Bar(win, "Progress", progress, "#90EE90", 100, 500)
    Print_Bar(win, "Trailer", trailer, "#8FBC8F", 200, 500)
    Print_Bar(win, "Retriever", retriever, "#556B2F", 300, 500)
    Print_Bar(win, "Exclude", exclude, "#FFB6C1", 400, 500)
##print text def calling
    Print_Text(win, add_text1 , 12, "black",110 , 550)
    Print_Text(win, total , 12, "black",185 , 550)
    Print_Text(win, add_text2 , 18, "black",150 , 50)
##text edit def calling    
    Text_Edit(win, progress ,progress, 12, "black", 100, 500)
    Text_Edit(win, trailer ,trailer, 12, "black", 200, 500)
    Text_Edit(win, retriever ,retriever, 12, "black", 300, 500)
    Text_Edit(win, exclude ,exclude, 12, "black", 400, 500)
##bar line def calling    
    Bar_line(win,a,b)

##inputs
def Input_marks():
    mark_list=[0, 20, 40, 60, 80, 100, 120]
    A=True
    while (A):
        pass_mark=input("Please enter your credits at pass:")
        try:
            pass_mark=int(pass_mark)
            if pass_mark not in mark_list:
                print("Out of range.")
            else:
                A=False
                
        except ValueError:
            print("Integer required.")
    B=True
    while (B):
        defer_mark=input("Please enter your credits at defer:")
        try:
            defer_mark=int(defer_mark)
            if defer_mark not in mark_list:
                print("Out of range.")
            else:
                B=False
                
        except ValueError:
            print("Integer required.")
    
    C=True
    while (C):
        fail_mark=input("Please enter your credits at fail:")
        try:
            fail_mark = int(fail_mark)
            if fail_mark not in mark_list:
                print("Out of range.")
            else:
                C=False

        except ValueError:
            print("Integer required.")
        
    return pass_mark, defer_mark, fail_mark
    

D=True
while (D):
    user_input = User()
    
    cumulative_counts = {'Progress': 0, 'Trailer': 0, 'Retriever': 0, 'Exclude': 0, 'Total': 0}

    print("\nPart 1 \n")
    E= True
    while (E):
##inputs def calling        
        pass_mark, defer_mark, fail_mark = Input_marks()

        if pass_mark + defer_mark + fail_mark != 120:
            print("Total incorrect.")
        else:
            P1, P2, P3, P4, P5 = progression_outcome(pass_mark, defer_mark, fail_mark)
            cumulative_counts['Progress']   +=P1
            cumulative_counts['Trailer']    +=P2
            cumulative_counts['Retriever']  +=P3
            cumulative_counts['Exclude']    +=P4
            cumulative_counts['Total']      +=P5
            print("")

            F=True
            while (F):
                outcome=input("Would you like to enter another set of data?\nEnter 'y' for yes or 'q' to quit and view results:")
                if outcome=="y":
                    print("")
                    F=False
                elif outcome=="q":
                    print("")
                    ##bar graph def calling                    
                    Bar_graph(cumulative_counts['Progress'], cumulative_counts['Trailer'],cumulative_counts['Retriever'], cumulative_counts['Exclude'],"outcomes in total.",cumulative_counts['Total'],"Histrogram Results",50,500)
                
                    F=False
                    E=False
                else:
                    print("Invalid response. Try again.")

    print("\nPart 2 \n")

    for data in all_inputs:
        print(data[0], "-", data[1], ",", data[2], ",", data[3])

    print("\nPart 3 \n")

    with open("Programme_Inputs.txt", "w") as file:
        for data in all_inputs:
            file.write(f"{data[0]} - {data[1]} , {data[2]} , {data[3]}\n")

    with open("Programme_Inputs.txt", "r") as file:
        file_data = file.read()

    print(file_data)
    D=False


