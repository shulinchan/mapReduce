import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * @author Shu Lin Chan, Jonathan Maeda, James Wang, Yaoming Zhan
 * Final Project
 */
public class Driver {
  public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    Path wiki = new Path(args[0]);
    Path out = new Path(args[1]);
    
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "cs132g4");
    
    job.setInputFormatClass(TextInputFormat.class);
    TextInputFormat.addInputPath(job, wiki);
    
    job.setOutputFormatClass(TextOutputFormat.class);
    TextOutputFormat.setOutputPath(job, out);

    job.setJarByClass(Driver.class);
    job.setMapperClass(WordMapper.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    job.setReducerClass(WordReducer.class);
    
    job.waitForCompletion(true);
  }
}